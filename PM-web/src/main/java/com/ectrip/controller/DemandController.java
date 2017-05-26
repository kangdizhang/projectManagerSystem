package com.ectrip.controller;

import com.ectrip.common.base.BaseController;
import com.ectrip.model.ProjectModle;
import com.ectrip.service.DemandService;
import com.ectrip.service.ModleService;
import com.ectrip.model.Demand;
import com.ectrip.service.ProjectService;
import com.ectrip.vo.DemandVO;
import com.ectrip.vo.ProjectModleVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/19 0019.
 */
@Controller
@RequestMapping(value = "/demand")
public class DemandController extends BaseController {

    @Autowired
    private DemandService demandService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ModleService modleService;

    @RequestMapping(value = "/demandList",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView projectList(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("demand/demandList");
        return modelAndView;
    }

    @RequestMapping(value="/completeDemand",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView completeDemand(Integer id,Integer projectId){
        ModelAndView modelAndView = new ModelAndView();
        demandService.updateDemand(id);
        modelAndView.addObject("projectId",projectId);
        modelAndView.setViewName("demand/demandList");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/list",method = {RequestMethod.GET,RequestMethod.POST})
    public Object demandList(Integer offset,Integer limit,String projectName,String demandName,String demandStatus){
        int pageNo = 1;
        if(offset != null) {
            pageNo = (offset/limit+1);
        }
        Integer projectId = 1;
        PageInfo<DemandVO> pageInfo = demandService.queryDemand(pageNo,limit,projectId,demandName,demandStatus);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("rows",pageInfo.getList());
        result.put("total",pageInfo.getTotal());
        return result;
    }

    @RequestMapping(value = "/editDemand",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView modleList(Integer projectId,Integer demandId) {
        ModelAndView modelAndView = new ModelAndView();

        //需求关联模块列表
        List<ProjectModle> list = modleService.findModleList(demandId);
        modelAndView.addObject("list", list);

        //项目模块列表
        List<ProjectModleVO> modleVOList = modleService.queryModleList(null, null, projectId, null).getList();

        //去重
        Iterator<ProjectModleVO> modleVOIterator = modleVOList.iterator();
        if(list != null && !list.isEmpty()){
            while (modleVOIterator.hasNext()){
                ProjectModleVO modleVO = modleVOIterator.next();
                for (ProjectModle modle:list) {
                    if (modle.getId()==modleVO.getId()){
                        modleVOIterator.remove();
                    }
                }
            }
        }

        modelAndView.addObject("ModleVOList", modleVOList);

        DemandVO demandVO = demandService.findDemand(demandId);
        modelAndView.addObject("demand", demandVO);
        modelAndView.addObject("projectId",projectId);
        modelAndView.addObject("demandId",demandId);
        modelAndView.setViewName("demand/editDemand");
        return modelAndView;
    }

    @RequestMapping(value = "/addDemand",method = {RequestMethod.GET})
    public ModelAndView addDemand(Integer projectId){
        List<ProjectModle> list = modleService.queryModleListByProjectId(projectId);
        ModelAndView mav = getModelAndView();
        mav.addObject("ModleVOList",list);
        mav.addObject("projectId",projectId);
        mav.setViewName("demand/editDemand");
        return mav;
    }

    @RequestMapping(value = "/saveDemand",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView saveDemand(Demand demand,Integer projectId,Integer demandId){
        String[] modleId = getRequest().getParameterValues("mdid");
        ModelAndView mav = getModelAndView();
        if(modleId == null || modleId.length ==0){
            return errorInfo(projectId,demandId,"请选择系统模块",mav,demand);
        }
        if(StringUtils.isEmpty(demand.getVersion())){
            return errorInfo(projectId,demandId,"版本号不能为空",mav,demand);
        }
        if(StringUtils.isEmpty(demand.getDemandName())){
            return errorInfo(projectId,demandId,"需求描述不能为空",mav,demand);
        }
        if(StringUtils.isEmpty(demand.getExceptEndTime())){
            return errorInfo(projectId,demandId,"预期完成时间不能为空",mav,demand);
        }
        List list = demandService.queryDemandByVeision(demand.getVersion(),projectId,demand.getId());
        if(!CollectionUtils.isEmpty(list)){
            return errorInfo(projectId,demandId,"版本号重复",mav,demand);
        }
        try {
            demandService.saveDemand(modleId,demand);
        }catch (Exception e){
            e.printStackTrace();
        }
        mav.addObject("projectId",projectId);
        mav.setViewName("demand/demandList");
        return mav;
    }

    public ModelAndView errorInfo(Integer projectId,Integer demandId,String errorMsg,ModelAndView mav,Demand demand){
        mav.addObject("errorMsg",errorMsg);
        List<ProjectModle> list = modleService.findModleList(demandId);
        mav.addObject("list", list);
        List<ProjectModleVO> modleVOList = modleService.queryModleList(null, null, projectId, null).getList();
        mav.addObject("ModleVOList", modleVOList);
        DemandVO demandVO = demandService.findDemand(demandId);
        mav.addObject("demand", demand);
        mav.addObject("projectId",projectId);
        mav.setViewName("demand/editDemand");
        return mav;
    }

    @RequestMapping(value = "/deleteDemand",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView deleteDemand(Integer id,Integer projectId){
        ModelAndView mav = getModelAndView();
        demandService.deleteDemand(id);
        mav.addObject("projectId",projectId);
        mav.setViewName("demand/demandList");
        return mav;
    }
}
