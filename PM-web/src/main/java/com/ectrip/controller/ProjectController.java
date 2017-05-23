package com.ectrip.controller;

import com.ectrip.common.base.BaseController;
import com.ectrip.model.Modle;
import com.ectrip.model.ModlePrototype;
import com.ectrip.model.Project;
import com.ectrip.service.ModlePrototypeService;
import com.ectrip.service.ModleService;
import com.ectrip.service.ProjectService;
import com.ectrip.vo.ModleVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 23626 on 2017/5/11.
 */
@Controller
@Transactional
@RequestMapping(value = "/project")
public class ProjectController extends BaseController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ModleService modleService;

    @Autowired
    private ModlePrototypeService modlePrototypeService;

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
    public ModelAndView saveProject(Integer id, String projectName,String projectLeader, String phone, String qq, String email, String projectStatus){
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

        String[] a = getRequest().getParameterValues("mpid");

        try{
            projectService.saveProject(a,id,projectName, projectLeader, phone, qq, email, projectStatus);
            mav.setViewName("project/projectList");
        }catch (Exception e){
            e.printStackTrace();
            mav.setViewName("project/addProject");
        }
        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "/addProject", method = {RequestMethod.GET,RequestMethod.POST})
    public Object modlePrototypeList(Integer id) {
        ModelAndView mav = getModelAndView();
        if (id != null){
            List<ModleVO> list = modleService.queryModleList(null,null,id,null,null).getList();
            mav.addObject("list", list);
        }else {
            List<ModlePrototype> list = modlePrototypeService.queryModlePrototype();
            mav.addObject("list", list);
        }
        Project project = projectService.queryProject(id);
        mav.addObject("project",project);

        mav.setViewName("project/addProject");
        return mav;
    }

    @RequestMapping(value = "/deleteProject",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView deleteModle(Integer id){
        ModelAndView mav = new ModelAndView();
        projectService.deleteProject(id);
        mav.setViewName("project/projectList");
        return mav;
    }
}
