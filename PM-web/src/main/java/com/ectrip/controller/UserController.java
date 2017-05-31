package com.ectrip.controller;

import com.ectrip.common.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 23626 on 2017/5/31.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    public ModelAndView login(String name,String pwd){
//        ModelAndView mav = new ModelAndView();
//        if(StringUtils.isEmpty(name)){
//            mav.addObject("msg","请输入用户名");
//        }
//        if(StringUtils.isEmpty(pwd)){
//            mav.addObject("msg","请输入密码");
//        }
        return null;
    }

}
