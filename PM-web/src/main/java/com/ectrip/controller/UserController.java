package com.ectrip.controller;

import com.ectrip.common.base.BaseController;
import com.ectrip.model.User;
import com.ectrip.service.UserService;
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
import java.util.Map;

/**
 * Created by 23626 on 2017/5/31.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView login(String userName,String pwd){
        ModelAndView mav = new ModelAndView();
        if(StringUtils.isEmpty(userName)){
            mav.addObject("msg","请输入用户名");
            mav.setViewName("login");
            return mav;
        }
        if(StringUtils.isEmpty(pwd)){
            mav.setViewName("login");
            mav.addObject("msg","请输入密码");
            return  mav;
        }
        if (userService.valUser(userName,pwd)){
            User user = userService.findUser(userName,pwd);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            user.setLastLoginTime(sdf.format(new Date()));
            getSession().setAttribute("userName",userName);
            getSession().setAttribute("user",user);
            userService.updateUser(user);
            mav.setViewName("WEB-INF/view/index");
            return mav;
        }
        mav.addObject("msg","用户名密码错误，请重新输入！");
        mav.setViewName("login");
        return mav;
    }

    @RequestMapping(value = "/userList",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView userList(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("WEB-INF/view/user/userList");
        return modelAndView;
    }

    @RequestMapping(value = "/list",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object pageList(Integer offset,Integer limit,String userName,String userType){
        int pageNo = 1;
        if (offset != null) {
            pageNo = (offset / limit + 1);
        }
        PageInfo<User> pageInfo = userService.queryUserByPageList(pageNo, limit, userName,userType);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("rows", pageInfo.getList());
        result.put("total", pageInfo.getTotal());
        return result;
    }

    @RequestMapping(value = "/editUser",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView editUser(Integer id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user0",userService.findUserById(id));
        modelAndView.setViewName("WEB-INF/view/user/editUser");
        return modelAndView;
    }

    @RequestMapping(value = "/addUser",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView addUser(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("WEB-INF/view/user/editUser");
        return modelAndView;
    }

    @RequestMapping(value = "/delUser",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView delUser(Integer id){
        ModelAndView modelAndView = new ModelAndView();
        userService.delUserById(id);
        modelAndView.setViewName("WEB-INF/view/user/userList");
        return modelAndView;
    }

    @RequestMapping(value = "/saveUser",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView saveUser(User user,String pwds){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user0",user);
        modelAndView.addObject("pwds",pwds);
        if (StringUtils.isEmpty(user.getUserName())) {
            modelAndView.addObject("msg","用户名不能为空！");
            modelAndView.setViewName("WEB-INF/view/user/editUser");
            return modelAndView;
        }
        if (StringUtils.isEmpty(user.getUserType())) {
            modelAndView.addObject("msg","请选择用户类型！");
            modelAndView.setViewName("WEB-INF/view/user/editUser");
            return modelAndView;
        }
        if (StringUtils.isEmpty(user.getPwd())) {
            modelAndView.addObject("msg","密码不能为空！");
            modelAndView.setViewName("WEB-INF/view/user/editUser");
            return modelAndView;
        }
        if (StringUtils.isEmpty(pwds)) {
            modelAndView.addObject("msg","请确认密码！");
            modelAndView.setViewName("WEB-INF/view/user/editUser");
            return modelAndView;
        }
        if (!user.getPwd().equals(pwds)) {
            modelAndView.addObject("msg","两次密码不一致，请重新确认！");
            modelAndView.setViewName("WEB-INF/view/user/editUser");
            return modelAndView;
        }
        if (user.getId() != null) {
            userService.updateUser(user);
        } else {
            userService.saveUser(user);
        }
        modelAndView.setViewName("WEB-INF/view/user/userList");
        return modelAndView;
    }

}
