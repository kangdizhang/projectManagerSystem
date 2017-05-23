package com.ectrip.controller;

import com.ectrip.common.base.BaseController;
import com.ectrip.service.ModleService;
import com.ectrip.service.VersionService;
import com.ectrip.vo.ModleVO;
import com.ectrip.vo.VersionVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
@Transactional
@RequestMapping("/version")
public class VersionController extends BaseController {
    @Autowired
    private VersionService versionService;

    @Autowired
    private ModleService modleService;

    @ResponseBody
    @RequestMapping(value = "/list",method = {RequestMethod.GET,RequestMethod.POST})
    public Object list(Integer offset,Integer limit,Integer modleId,String versionState){
        int pageNo = 1;
        if(offset != null) {
            pageNo = (offset/limit+1);
        }
        PageInfo<VersionVO> pageInfo = versionService.queryVersion(offset,limit,modleId,versionState);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("rows",pageInfo.getList());
        result.put("total",pageInfo.getTotal());
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/viewVerisonList",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView viewVerisonList(Integer modleId,Integer projectId){
        ModelAndView modelAndView = new ModelAndView();
        List<ModleVO> list = modleService.queryModleList(null,null,projectId,null,null).getList();
        modelAndView.addObject("list",list);
        modelAndView.addObject("ModleId",modleId);
        modelAndView.setViewName("version/versionList");
        return modelAndView;
    }
}
