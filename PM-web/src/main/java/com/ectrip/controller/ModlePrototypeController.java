package com.ectrip.controller;

import com.ectrip.common.base.BaseController;
import com.ectrip.model.ModlePrototype;
import com.ectrip.service.ModlePrototypeService;
import com.ectrip.vo.ModleVersionVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
@RequestMapping(value = "/modlePrototype")
public class ModlePrototypeController extends BaseController {

    @Autowired
    private ModlePrototypeService modlePrototypeService;

    @RequestMapping(value = "/editModlePrototype",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView editModlePrototype(Integer id){
        ModelAndView modelAndView = new ModelAndView();
        ModleVersionVO ModleVersionVO = modlePrototypeService.findModlePrototype(id);
        modelAndView.addObject("modlePrototype",ModleVersionVO);
        modelAndView.setViewName("view/modle/editModlePrototype");
        return modelAndView;
    }

    @RequestMapping(value = "/modlePrototypeList",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView projectList(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("view/modle/modlePrototypeList");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
    public Object list(Integer offset, Integer limit, String modlePrototypeName) {
        int pageNo = 1;
        if (offset != null) {
            pageNo = (offset / limit + 1);
        }
        PageInfo<ModlePrototype> pageInfo = modlePrototypeService.findModlePrototypeListPage(pageNo, limit, modlePrototypeName);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("rows", pageInfo.getList());
        result.put("total", pageInfo.getTotal());
        return result;
    }

    @RequestMapping(value = "/saveProjectInfo", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView saveProject(ModlePrototype modlePrototype) {
        ModelAndView mav = getModelAndView();
        mav.addObject("modlePrototype", modlePrototype);
        if (StringUtils.isEmpty(modlePrototype.getModlePrototypeName())) {
            mav.addObject("msg", "模块原型名称不能为空");
            mav.setViewName("view/modle/editModlePrototype");
            return mav;
        }
        if (StringUtils.isEmpty(modlePrototype.getModlePrototypeDescribe())) {
            mav.addObject("msg", "模块原型描述不能为空");
            mav.setViewName("view/modle/editModlePrototype");
            return mav;
        }
        modlePrototypeService.saveModlePrototype(modlePrototype);
        mav.setViewName("view/modle/modlePrototypeList");
        return mav;
    }
}
