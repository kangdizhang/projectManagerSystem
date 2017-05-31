package com.ectrip.controller;

import com.ectrip.common.base.BaseController;
import com.ectrip.model.ModlePrototype;
import com.ectrip.service.ModlePrototypeService;
import com.ectrip.service.ModleService;
import com.ectrip.service.VersionService;
import com.ectrip.vo.ModleVersionVO;
import com.ectrip.vo.ProjectModleVO;
import com.ectrip.vo.VersionVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/22 0022.
 */
@Controller
@RequestMapping("/version")
public class VersionController extends BaseController {
    @Autowired
    private VersionService versionService;

    @Autowired
    private ModlePrototypeService modlePrototypeService;

    @ResponseBody
    @RequestMapping(value = "/list",method = {RequestMethod.GET,RequestMethod.POST})
    public Object list(Integer offset,Integer limit,Integer modleId){
        int pageNo = 1;
        if(offset != null) {
            pageNo = (offset/limit+1);
        }
        PageInfo<VersionVO> pageInfo = versionService.queryVersion(offset,limit,modleId);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("rows",pageInfo.getList());
        result.put("total",pageInfo.getTotal());
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/versionList",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView viewVerisonList(Integer modleId){
        ModelAndView modelAndView = new ModelAndView();

        ModleVersionVO modleVersionVO = modlePrototypeService.findModlePrototype(modleId);
        modelAndView.addObject("modlePrototype",modleVersionVO);

        List<ModleVersionVO> list = modlePrototypeService.queryModlePrototype();
        modelAndView.addObject("list",list);
        modelAndView.setViewName("view/version/versionList");
        return modelAndView;
    }
}
