package com.ectrip.controller;

import com.ectrip.common.base.BaseController;
import com.ectrip.model.ModlePrototype;
import com.ectrip.model.Project;
import com.ectrip.model.ProjectInfo;
import com.ectrip.service.ModlePrototypeService;
import com.ectrip.service.ModleService;
import com.ectrip.service.ProjectService;
import com.ectrip.vo.ModleVersionVO;
import com.ectrip.vo.ProjectModleVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 23626 on 2017/5/11.
 */
@Controller
@RequestMapping(value = "/project")
public class ProjectController extends BaseController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ModleService modleService;

    @Autowired
    private ModlePrototypeService modlePrototypeService;

    @RequestMapping(value = "/projectList",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView projectList(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("WEB-INF/view/project/projectList");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/list",method = {RequestMethod.GET,RequestMethod.POST})
    public Object list(Integer offset,Integer limit,String projectStatus,String projectName,String projectLeader) {
        int pageNo = 1;
        if(offset != null) {
            pageNo = (offset/limit+1);
        }
        PageInfo<Project> pageInfo = projectService.findProjectListPage(pageNo,limit,projectStatus,projectName,projectLeader);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("rows",pageInfo.getList());
        result.put("total",pageInfo.getTotal());
        return result;
    }

    @RequestMapping(value = "/saveProject",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView saveProject(Project project, ProjectInfo projectInfo){

        ModelAndView mav = getModelAndView();

        if (project.getId() != null){
            List<ModleVersionVO> list = modlePrototypeService.queryModlePrototypeById(project.getId());
            mav.addObject("list", list);
        }else {
            List<ModleVersionVO> list = modlePrototypeService.queryModlePrototype();
            mav.addObject("list", list);
        }

        mav.setViewName("WEB-INF/view/project/addProject");
        if(StringUtils.isEmpty(project.getProjectName())){
            mav.addObject("msg","项目名称不能为空");
            return mav;
        }
        String[] mpid = getRequest().getParameterValues("mpid");
        String[] modleIds = getRequest().getParameterValues("modleId");
        String[] version = getRequest().getParameterValues("version");

        if(validateProject(project,mpid,version,mav)){
            return mav;
        }

        if(project.getId()==null){
            Map map = validateProjectInfo(projectInfo);
            if((Boolean)map.get("flag")==false){
                projectInfo = null;
            }else{
                if(!StringUtils.isEmpty(map.get("errInfo"))){
                    mav.addObject("msg",map.get("errInfo"));
                    return mav;
                }
            }
        }

        try{
            projectService.saveProject(project,projectInfo,modleIds,version,mpid);
            mav.setViewName("WEB-INF/view/project/projectList");
        }catch (Exception e){
            e.printStackTrace();
            mav.setViewName("WEB-INF/view/project/addProject");
        }
        return mav;
    }

    boolean validateProject(Project project,String[] mpid,String[] version,ModelAndView mav){
        boolean flag = false;
        if(project.getId()==null){
            if(mpid == null || mpid.length == 0){
                mav.addObject("msg","请选择系统模块");
                flag=true;
                return flag;
            }
            Integer j ;
            for(int i = 0;i<mpid.length;i++){
                j = Integer.valueOf(mpid[i]);
                if(StringUtils.isEmpty(version[j])){
                    mav.addObject("msg","选中模块请选择版本号");
                    mav.setViewName("WEB-INF/view/project/addProject");
                    flag=true;
                }
            }

        }
        if(StringUtils.isEmpty(project.getRegion())){
            mav.addObject("msg","项目所在地不能为空");
            flag=true;
        }
        if(StringUtils.isEmpty(project.getProjectLeader())){
            mav.addObject("msg","项目负责人不能为空");
            flag=true;
        }
        if(StringUtils.isEmpty(project.getPhone())){
            mav.addObject("msg","负责人电话不能为空");
            flag=true;
        }
        if(StringUtils.isEmpty(project.getEmail())){
            mav.addObject("msg","负责人邮箱不能为空");
            flag=true;
        }
        if(StringUtils.isEmpty(project.getEmail())){
            mav.addObject("msg","负责人邮箱不能为空");
            flag=true;
        }
        return flag;
    }

    Map validateProjectInfo(ProjectInfo projectInfo){
        boolean flag = false;
        String errInfo = null;
        Map map = new HashMap();
        if(StringUtils.isEmpty(projectInfo.getServerIp())){
            errInfo = "服务器IP不能为空";
        }else{
            flag=true;
        }
        if(StringUtils.isEmpty(projectInfo.getOptSystem())){
            errInfo = "操作系统不能为空";
        }else{
            flag=true;
        }
        if(StringUtils.isEmpty(projectInfo.getDbServerIp())){
            errInfo = "数据库服务器IP不能为空";
        }else{
            flag=true;
        }
        if(StringUtils.isEmpty(projectInfo.getDbUser())){
            errInfo = "数据库用户名不能为空";
        }else{
            flag=true;
        }
        if(StringUtils.isEmpty(projectInfo.getDbPwd())){
            errInfo = "数据库密码不能为空";
        }else{
            flag=true;
        }
        if(projectInfo.getDbPort()==null){
            errInfo = "数据库端口号不能为空";
        }else{
            flag=true;
        }
        if(StringUtils.isEmpty(projectInfo.getHostName())){
            errInfo = "域名不能为空";
        }else{
            flag=true;
        }
        if(StringUtils.isEmpty(projectInfo.getSsh())){
            errInfo = "ssh不能为空";
        }else{
            flag=true;
        }
        if(StringUtils.isEmpty(projectInfo.getNote())){
            errInfo = "备注不能为空";
        }else{
            flag=true;
        }
        map.put("flag",flag);
        map.put("errInfo",errInfo);
        return map;
    }

    boolean projectInfoIsNull(ProjectInfo projectInfo){
        boolean flag = false;
        if(!StringUtils.isEmpty(projectInfo.getServerIp())){
            flag = true;
            return flag;
        }
        if(!StringUtils.isEmpty(projectInfo.getOptSystem())){
            flag = true;
            return flag;
        }
        if(!StringUtils.isEmpty(projectInfo.getDbServerIp())){
            flag = true;
            return flag;
        }
        if(!StringUtils.isEmpty(projectInfo.getDbUser())){
            flag = true;
            return flag;
        }
        if(projectInfo.getDbPort() != null){
            flag = true;
            return flag;
        }
        if(!StringUtils.isEmpty(projectInfo.getHostName())){
            flag = true;
            return flag;
        }
        if(!StringUtils.isEmpty(projectInfo.getSsh())){
            flag = true;
            return flag;
        }
        if(!StringUtils.isEmpty(projectInfo.getNote())){
            flag = true;
            return flag;
        }
        return flag;
    }

    @ResponseBody
    @RequestMapping(value = "/addProject", method = {RequestMethod.GET,RequestMethod.POST})
    public Object modlePrototypeList(Integer id) {
        ModelAndView mav = getModelAndView();
        if (id != null){
            List<ModleVersionVO> list = modlePrototypeService.queryModlePrototypeById(id);
            mav.addObject("list", list);
        }else {
            List<ModleVersionVO> list = modlePrototypeService.queryModlePrototype();
            mav.addObject("list", list);
        }
        Project project = projectService.queryProject(id);
        mav.addObject("project",project);
        mav.setViewName("WEB-INF/view/project/addProject");
        return mav;
    }

    @RequestMapping(value = "/deleteProject",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView deleteModle(Integer id){
        ModelAndView mav = new ModelAndView();
        projectService.deleteProject(id);
        mav.setViewName("WEB-INF/view/project/projectList");
        return mav;
    }
}
