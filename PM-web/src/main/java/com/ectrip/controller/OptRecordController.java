package com.ectrip.controller;

import com.ectrip.common.base.BaseController;
import com.ectrip.common.base.BaseResult;
import com.ectrip.service.OptManageService;
import com.ectrip.vo.OptRecordAndEnvVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by huangxinguang on 2017/4/20 上午10:08.
 * </p>
 * Desc:
 */
@Controller
@RequestMapping("/optManage")
public class OptRecordController extends BaseController {

    @Autowired
    private OptManageService optManageService;


    @ResponseBody
    @RequestMapping(value = "/doOperate",method = RequestMethod.GET)
    public Object doOperate(HttpServletRequest request,String remoteUser,String remoteIp,String sessionId,String userAgent, String reqUrl,
                                        String reqAction,String reqParams,String sceneNo,String userId,String sysCode,String channelCode,String optBrief,String optDesc) {
        try {
            optManageService.saveOptAndEnv(request, remoteUser, remoteIp, sessionId, userAgent, reqUrl, reqAction, reqParams, sceneNo, userId, sysCode, channelCode, optBrief, optDesc);
            return new BaseResult().ok();

        }catch (Exception e) {
            e.printStackTrace();
        }

        return  new BaseResult().notOk();
    }


    @RequestMapping(value = "/optRecordPage",method = RequestMethod.GET)
    public ModelAndView optRecordPage() {
        ModelAndView mav = getModelAndView();
        mav.setViewName("record/optList");
        return mav;
    }

    /**
     * 分页查询并返回json
     * @param offset
     * @param limit
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Object list(Integer offset,Integer limit) {
        int pageNo = 1;
        if(offset != null) {
            pageNo = (offset/limit+1);
        }
        PageInfo<OptRecordAndEnvVO> pageInfo = optManageService.findOptRecordAndEnvListPage(pageNo,limit,null,null,null,null,null,null,null,null);

        Map<String,Object> result = new HashMap<String,Object>();
        result.put("rows",pageInfo.getList());
        result.put("total",pageInfo.getTotal());
        return result;
    }

}
