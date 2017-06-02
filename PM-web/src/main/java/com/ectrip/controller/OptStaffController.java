package com.ectrip.controller;

import com.ectrip.common.base.BaseController;
import com.ectrip.model.OptStaff;
import com.ectrip.model.Project;
import com.ectrip.model.User;
import com.ectrip.service.OptStaffService;
import com.ectrip.service.ProjectService;
import com.ectrip.service.UserService;
import com.ectrip.vo.OptStaffVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 23626 on 2017/5/31.
 */
@Controller
@RequestMapping(value = "/optStaff")
public class OptStaffController extends BaseController {
    @Autowired
    private OptStaffService optStaffService;

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/optStaffList",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView optStaffList(){
        ModelAndView modelAndView = new ModelAndView();
        List<Project> list = projectService.findProjectListPage(null,null,null,null,null).getList();
        modelAndView.addObject("list",list);
        modelAndView.setViewName("WEB-INF/view/optStaff/optStaffList");
        return modelAndView;
    }

    @RequestMapping(value = "/list",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object pageList(Integer offset,Integer limit,Integer projectId,String optStaffName){
        int pageNo = 1;
        if (offset != null) {
            pageNo = (offset / limit + 1);
        }
        PageInfo<OptStaffVO> pageInfo = optStaffService.queryOptStaffByPageList(offset,limit,projectId, optStaffName);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("rows", pageInfo.getList());
        result.put("total", pageInfo.getTotal());
        return result;
    }

    @RequestMapping(value = "/editOptStaff",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView editOptStaff(Integer id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("optStaff",optStaffService.findOptStaffById(id));
        List<Project> list = projectService.findProjectListPage(null,null,null,null,null).getList();
        modelAndView.addObject("list",list);
        modelAndView.setViewName("WEB-INF/view/optStaff/editOptStaff");
        return modelAndView;
    }

    @RequestMapping(value = "/addOptStaff",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView addUser(){
        ModelAndView modelAndView = new ModelAndView();
        List<Project> list = projectService.findProjectListPage(null,null,null,null,null).getList();
        modelAndView.addObject("list",list);
        modelAndView.setViewName("WEB-INF/view/optStaff/editOptStaff");
        return modelAndView;
    }

    @RequestMapping(value = "/delOptStaff",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView delUser(Integer id){
        ModelAndView modelAndView = new ModelAndView();
        optStaffService.delOptStaffById(id);
        List<Project> list = projectService.findProjectListPage(null,null,null,null,null).getList();
        modelAndView.addObject("list",list);
        modelAndView.setViewName("WEB-INF/view/optStaff/optStaffList");
        return modelAndView;
    }

    @RequestMapping(value = "/saveOptStaff",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView saveUser(OptStaff optStaff){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("optStaff",optStaff);
        List<Project> list = projectService.findProjectListPage(null,null,null,null,null).getList();
        modelAndView.addObject("list",list);
        if (StringUtils.isEmpty(optStaff.getProjectId())) {
            modelAndView.addObject("msg","请选择项目！");
            modelAndView.setViewName("WEB-INF/view/optStaff/editOptStaff");
            return modelAndView;
        }
        if (StringUtils.isEmpty(optStaff.getOptStaffName())) {
            modelAndView.addObject("msg","运维人员名不能为空！");
            modelAndView.setViewName("WEB-INF/view/optStaff/editOptStaff");
            return modelAndView;
        }
        if (StringUtils.isEmpty(optStaff.getTel())) {
            modelAndView.addObject("msg","手机号不能为空！");
            modelAndView.setViewName("WEB-INF/view/optStaff/editOptStaff");
            return modelAndView;
        }
        if (StringUtils.isEmpty(optStaff.getEmail())) {
            modelAndView.addObject("msg","邮箱不能为空！");
            modelAndView.setViewName("WEB-INF/view/optStaff/editOptStaff");
            return modelAndView;
        }
        if (optStaff.getId() != null) {
            optStaffService.updateOptStaff(optStaff);
        } else {
            optStaffService.saveOptStaff(optStaff);
        }
        modelAndView.setViewName("WEB-INF/view/optStaff/optStaffList");
        return modelAndView;
    }

}
