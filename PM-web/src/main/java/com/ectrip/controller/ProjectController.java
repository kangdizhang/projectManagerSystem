package com.ectrip.controller;

import com.ectrip.common.base.BaseController;
import com.ectrip.model.ModlePrototype;
import com.ectrip.model.Project;
import com.ectrip.service.ModlePrototypeService;
import com.ectrip.service.ProjectService;
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
    private ModlePrototypeService modlePrototypeService;

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

    @RequestMapping(value = "/saveProject",method = RequestMethod.GET)
    public ModelAndView saveProject(Integer id,String projectName, String projectLeader, String phone, String qq, String email, String projectStatus){
        ModelAndView mav = getModelAndView();
        if(StringUtils.isEmpty(projectName)){
            mav.addObject("msg","项目名称不能为空");
            mav.setViewName("project/addProject");
            return mav;
        }
        if(StringUtils.isEmpty(projectLeader)){
            mav.addObject("msg","项目负责人不能为空");
            mav.setViewName("project/addProject");
            return mav;
        }
        if(StringUtils.isEmpty(phone)){
            mav.addObject("msg","负责人电话不能为空");
            mav.setViewName("project/addProject");
            return mav;
        }
        if(StringUtils.isEmpty(email)){
            mav.addObject("msg","负责人邮箱不能为空");
            mav.setViewName("project/addProject");
            return mav;
        }

        try{
        projectService.saveProject(id,projectName, projectLeader, phone, qq, email, projectStatus);
            mav.setViewName("project/projectList");
        }catch (Exception e){
            e.printStackTrace();
            mav.setViewName("project/addProject");
        }
        return mav;
    }



    @ResponseBody
    @RequestMapping(value = "/addProject", method = RequestMethod.GET)
    public Object modlePrototypeList() {
        ModelAndView mav = getModelAndView();
        List<ModlePrototype> list = modlePrototypeService.queryModlePrototype();
        mav.addObject("rows", list);
        mav.setViewName("project/addProject");
        return mav;
    }
}
