package com.ectrip.controller;

import com.ectrip.common.base.BaseController;
import com.ectrip.model.Project;
import com.ectrip.service.ProjectService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 23626 on 2017/5/11.
 */
@Controller
@RequestMapping(value = "/project")
public class ProjectController extends BaseController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/projectPage",method = RequestMethod.GET)
    public ModelAndView optRecordPage() {
        ModelAndView mav = getModelAndView();
        mav.setViewName("projectList");
        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.GET)
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

    @RequestMapping(value = "/addProject",method = RequestMethod.GET)
    public ModelAndView addProject() {
        ModelAndView mav = getModelAndView();
        mav.setViewName("addProject");
        return mav;
    }

    @RequestMapping(value = "/saveProject",method = RequestMethod.GET)
    public ModelAndView saveProject(String projectName, String projectLeader, String phone, String QQ, String email, String operateTime, String projectStatus){
        projectService.saveProject(projectName, projectLeader, phone, QQ, email, operateTime, projectStatus);
        ModelAndView mav = getModelAndView();
        mav.setViewName("addProjectSuccess");
        return mav;
    }

    @RequestMapping(value = "/editProject",method = RequestMethod.GET)
    public Object editProject(Integer id){
        System.out.println(id);
        return null;
    }
}
