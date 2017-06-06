package com.ectrip.controller;

import com.ectrip.common.base.BaseController;
import com.ectrip.model.*;
import com.ectrip.service.DemandService;
import com.ectrip.service.ModlePrototypeService;
import com.ectrip.service.ModleService;
import com.ectrip.service.ProjectService;
import com.ectrip.vo.DemandVO;
import com.ectrip.vo.ModleVersionVO;
import com.ectrip.vo.ProjectModleVO;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/5/19 0019.
 */
@Controller
@RequestMapping(value = "/demand")
public class DemandController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(DemandController.class);

    @Autowired
    private DemandService demandService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ModleService modleService;

    @Autowired
    private ModlePrototypeService modlePrototypeService;

    @RequestMapping(value = "/demandList",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView projectList(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("WEB-INF/view/demand/demandList");
        List<Project> list = projectService.findProjectListPage(null, null, null, null, null).getList();
        modelAndView.addObject("list", list);
        return modelAndView;
    }

    @RequestMapping(value="/demandComplete",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView demandComplete(Integer id,Integer projectId){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("id",id);
        modelAndView.addObject("projectId",projectId);
        modelAndView.setViewName("WEB-INF/view/demand/completeDemand");
        return modelAndView;
    }

    @RequestMapping(value="/completeDemand",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView completeDemand(@RequestParam("sqlfile") CommonsMultipartFile sqlfile, Integer id, Integer projectId, HttpServletRequest request, String versionDesc){
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) getRequest().getSession().getAttribute("user");

        if (StringUtils.isEmpty(versionDesc)) {
            modelAndView.addObject("sqlfile",sqlfile);
            modelAndView.addObject("msg","备注说明不能为空！");
            modelAndView.setViewName("WEB-INF/view/demand/completeDemand");
            return modelAndView;
        }

        demandService.updateDemand(id, user.getUserName(),versionDesc);
        Project project = projectService.queryProject(demandService.findDemand(id).getProjectId());
        //文件保存
        if (sqlfile != null) {
            logger.info("上传的SQL文件名为："+sqlfile.getOriginalFilename());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dtime = df.format(new Date());
            String path = "/SQLFile/"+project.getProjectName();
//        String path = "/SQLFile/"+dtime.substring(0, 4)+"/"+dtime.substring(5, 7)+"/"+dtime.substring(8, 10);
            String realPath=request.getSession().getServletContext().getRealPath(path);
            //判断目录是否存在（创建目录）
            if(!new File(realPath).exists()){
                new File(realPath).mkdirs();
            }
            File file = new File(realPath,sqlfile.getOriginalFilename());
            try {
                sqlfile.transferTo(file);
            } catch ( IOException e) {
                logger.info("文件保存异常");
            }
        }

        modelAndView.addObject("projectId",projectId);
        modelAndView.setViewName("WEB-INF/view/demand/demandList");
        List<Project> list = projectService.findProjectListPage(null, null, null, null, null).getList();
        modelAndView.addObject("list", list);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/list",method = {RequestMethod.GET,RequestMethod.POST})
    public Object demandList(Integer offset,Integer limit,Integer projectId,String demandName,String demandStatus){
        int pageNo = 1;
        if(offset != null) {
            pageNo = (offset/limit+1);
        }
        PageInfo<DemandVO> pageInfo = demandService.queryDemand(pageNo,limit,projectId,demandName,demandStatus);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("rows",pageInfo.getList());
        result.put("total",pageInfo.getTotal());
        return result;
    }

    @RequestMapping(value = "/editDemand",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView modleList(Integer demandId) {
        ModelAndView modelAndView = new ModelAndView();

        DemandVO demandVO = demandService.findDemand(demandId);
        Integer projectId = demandVO.getProjectId();
        //需求关联模块列表
        List<ProjectModleVO> list = modleService.findModleList(demandId);
        modelAndView.addObject("list", list);

        //项目模块列表
        List<ProjectModleVO> modleVOList = modleService.queryModleList(null, null, projectId, null).getList();

        //去重
        Iterator<ProjectModleVO> modleVOIterator = modleVOList.iterator();
        if(list != null && !list.isEmpty()){
            while (modleVOIterator.hasNext()){
                ProjectModleVO modleVO = modleVOIterator.next();
                for (ProjectModleVO modle:list) {
                    if (modle.getModleId()==modleVO.getModleId()){
                        modleVOIterator.remove();
                    }
                }
            }
        }

        modelAndView.addObject("ModleVOList", modleVOList);
        Project project = projectService.queryProject(projectId);
        modelAndView.addObject("demand", demandVO);
        modelAndView.addObject("projectId",projectId);
        modelAndView.addObject("projectName",project.getProjectName());
        modelAndView.addObject("demandId",demandId);
        modelAndView.setViewName("WEB-INF/view/demand/editDemand");
        return modelAndView;
    }

    @RequestMapping(value = "/addDemand",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView addDemand(Integer projectName){
        ModelAndView mav = getModelAndView();
        if (projectName == null) {
            mav.addObject("msg","请选择项目！");
            mav.setViewName("WEB-INF/view/demand/demandList");
            List<Project> list = projectService.findProjectListPage(null, null, null, null, null).getList();
            mav.addObject("list", list);
            return mav;
        }
        List<ModlePrototype> list = modlePrototypeService.queryModlePrototypeByProjectId(projectName);
        mav.addObject("ModleVOList",list);
        mav.addObject("projectId",projectName);
        mav.setViewName("WEB-INF/view/demand/editDemand");
        Project project = projectService.queryProject(projectName);
        mav.addObject("projectName",project.getProjectName());
        return mav;
    }

    @RequestMapping(value = "/saveDemand",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView saveDemand(Demand demand,Integer pid,Integer projectId, Integer demandId){
        String[] modleId = getRequest().getParameterValues("mdid");
        ModelAndView mav = getModelAndView();
        User user = (User) getRequest().getSession().getAttribute("user");
        if(modleId == null || modleId.length ==0){
            return errorInfo(pid,demandId,"请选择系统模块",mav,demand);
        }
        if(StringUtils.isEmpty(demand.getVersion())){
            return errorInfo(pid,demandId,"版本号不能为空",mav,demand);
        }
        if(StringUtils.isEmpty(demand.getDemandName())){
            return errorInfo(pid,demandId,"需求描述不能为空",mav,demand);
        }
        if(StringUtils.isEmpty(demand.getExceptEndTime())){
            return errorInfo(pid,demandId,"预期完成时间不能为空",mav,demand);
        }
        List list = demandService.queryDemandByVeision(demand.getVersion(),projectId,demand.getId());
        if(!CollectionUtils.isEmpty(list)){
            return errorInfo(projectId,demandId,"版本号重复",mav,demand);
        }

        demandService.saveDemand(modleId,demand,user.getUserName());

        List<Project> projectList = projectService.findProjectListPage(null, null, null, null, null).getList();
        mav.addObject("list", projectList);
        mav.setViewName("WEB-INF/view/demand/demandList");
        return mav;
    }

    @RequestMapping(value = "/deleteDemand",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView deleteDemand(Integer id,Integer projectId){
        ModelAndView mav = getModelAndView();
        demandService.deleteDemand(id);
        List<Project> list = projectService.findProjectListPage(null, null, null, null, null).getList();
        mav.addObject("list", list);
        mav.addObject("projectId",projectId);
        mav.setViewName("WEB-INF/view/demand/demandList");
        return mav;
    }

    public ModelAndView errorInfo(Integer projectId,Integer demandId,String errorMsg,ModelAndView mav,Demand demand){
        mav.addObject("errorMsg",errorMsg);
        List<ProjectModleVO> list = modleService.findModleList(demandId);
        mav.addObject("list", list);
        List<ProjectModleVO> modleVOList = modleService.queryModleList(null, null, projectId, null).getList();
        Project project = projectService.queryProject(projectId);
        demand.setVersion(null);
        mav.addObject("ModleVOList", modleVOList);
//        DemandVO demandVO = demandService.findDemand(demandId);
        mav.addObject("demand", demand);
        mav.addObject("projectId",projectId);
        mav.addObject("projectName",project.getProjectName());
        mav.setViewName("WEB-INF/view/demand/editDemand");
        return mav;
    }
}
