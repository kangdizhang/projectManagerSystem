package com.ectrip.controller;

import com.ectrip.common.base.BaseController;
import com.ectrip.model.Modle;
import com.ectrip.service.DemandService;
import com.ectrip.service.ModleService;
import com.ectrip.model.Demand;
import com.ectrip.model.Modle;
import com.ectrip.service.DemandService;
import com.ectrip.service.ModleService;
import com.ectrip.service.ProjectService;
import com.ectrip.vo.DemandVO;
import com.ectrip.vo.ModleVO;
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
 * Created by Administrator on 2017/5/19 0019.
 */
@Controller
@RequestMapping(value = "/demand")
public class DemandController extends BaseController {

    @Autowired
    private DemandService demandService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ModleService modleService;

    @ResponseBody
    @RequestMapping(value = "/list",method = {RequestMethod.GET,RequestMethod.POST})
    public Object demandList(Integer offset,Integer limit,Integer projectId,String demandName,String demandStatus){
        int pageNo = 1;
        if(offset != null) {
            pageNo = (offset/limit+1);
        }
        PageInfo<DemandVO> pageInfo = demandService.queryDemand(pageNo,limit,projectId,demandName,demandStatus);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("rows",pageInfo.getList());
        result.put("total",pageInfo.getTotal());
        return result;
    }

    @RequestMapping(value = "/editDemand",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView modleList(Integer projectId,Integer demandId) {
        ModelAndView modelAndView = new ModelAndView();
        List<Modle> list = modleService.findModleList(demandId);
        modelAndView.addObject("list", list);

        List<ModleVO> modleVOList = modleService.queryModleList(null, null, projectId, null, null).getList();
        modelAndView.addObject("ModleVOList", modleVOList);

        DemandVO demandVO = demandService.findDemand(demandId);
        modelAndView.addObject("demand", demandVO);

        modelAndView.setViewName("/demand/editDemand");
        return modelAndView;
    }

    @RequestMapping(value = "/addDemand",method = {RequestMethod.GET})
    public ModelAndView addDemand(Integer projectId){
        List<Modle> list = modleService.queryModleListByProjectId(projectId);
        ModelAndView mav = getModelAndView();
        mav.addObject("ModleVOList",list);
        mav.setViewName("demand/editDemand");
        return mav;
    }


    @RequestMapping(value = "/saveDemand",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView saveDemand(Demand demand){
        String[] modleId = getRequest().getParameterValues("mdid");
        ModelAndView mav = getModelAndView();
        try {
            demandService.saveDemand(modleId,demand);
            mav.setViewName("demand/demandList");
            return mav;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
