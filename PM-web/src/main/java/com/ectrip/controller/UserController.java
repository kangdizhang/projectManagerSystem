package com.ectrip.controller;

import com.ectrip.common.base.BaseController;
import com.ectrip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
            mav.setViewName("view/index");
            return mav;
        }
        mav.addObject("msg","用户名密码错误，请重新输入！");
        mav.setViewName("login");
        return mav;
    }

}
