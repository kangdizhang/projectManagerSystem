package com.ectrip.controller;

import com.ectrip.common.base.BaseController;
import com.ectrip.model.Project;
import com.ectrip.model.ProjectInfo;
import com.ectrip.service.ProjectService;
import com.ectrip.vo.ProjectInfoVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 23626 on 2017/5/11.
 */
@Controller
@RequestMapping(value = "/projectInfo")
public class ProjectInfoController extends BaseController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/projectInfoList",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView projectInfoList(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("projectInfo/projectInfoList");
        return modelAndView;
    }

    @RequestMapping(value = "/editProjectInfo",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView editProjectInfo(Integer id){
        ModelAndView mav = getModelAndView();
        ProjectInfoVO projectInfoVO = projectService.queryProjectInfo(id);
        mav.addObject("projectInfoVO",projectInfoVO);
        mav.setViewName("projectInfo/editProjectInfo");
        return mav;
    }

    @RequestMapping(value = "/addProjectInfo",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView addProjectInfo(){
        ModelAndView mav = getModelAndView();
        List<Project> list = projectService.findProjectListPage(null,null,null,null,null).getList();
        if (CollectionUtils.isEmpty(list)) {
            mav.setViewName("projectInfo/errorPage");
            return mav;
        }
        mav.addObject("list",list);
        mav.setViewName("projectInfo/editProjectInfo");
        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "/list",method = {RequestMethod.GET,RequestMethod.POST})
    public Object list(Integer offset,Integer limit,String projectName) {
        int pageNo = 1;
        if(offset != null) {
            pageNo = (offset/limit+1);
        }
        PageInfo<ProjectInfoVO> pageInfo = projectService.findProjectInfoListPage(pageNo,limit,projectName);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("rows",pageInfo.getList());
        result.put("total",pageInfo.getTotal());
        return result;
    }

    @RequestMapping(value = "/delProjectInfo",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView delProjectInfo(Integer id){
        ModelAndView modelAndView = new ModelAndView();
        projectService.delProjectInfo(id);
        modelAndView.setViewName("projectInfo/projectInfoList");
        return modelAndView;
    }

    @RequestMapping(value = "/saveProjectInfo",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView saveProjectInfo(ProjectInfo projectInfo){
        ModelAndView mav = getModelAndView();
        if (projectInfo.getProjectId() == 0) {
            mav.addObject("msg","请选择项目！");
            mav.setViewName("projectInfo/editProjectInfo");
        }
        projectService.saveProjectInfo(projectInfo);
        mav.setViewName("projectInfo/projectInfoList");
        return mav;
    }
}
