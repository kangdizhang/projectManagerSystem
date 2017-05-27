package com.ectrip.controller;

import com.ectrip.common.base.BaseController;
import com.ectrip.model.Project;
import com.ectrip.model.ProjectModle;
import com.ectrip.model.ModlePrototype;
import com.ectrip.service.ModlePrototypeService;
import com.ectrip.service.ModleService;
import com.ectrip.service.ProjectService;
import com.ectrip.service.VersionService;
import com.ectrip.vo.ModleVersionVO;
import com.ectrip.vo.ProjectModleVO;
import com.ectrip.vo.VersionVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hxgdsb on 2017/5/16 0016.
 */
@Controller
@RequestMapping(value = "/modle")
public class ModleController extends BaseController {

    @Autowired
    private ModleService modleService;

    @Autowired
    private VersionService versionService;

    @Autowired
    private ModlePrototypeService modlePrototypeService;

    @Autowired
    private ProjectService projectService;

    @ResponseBody
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public Object modleList(Integer offset, Integer limit, Integer projectId, String modleName) {
        int pageNo = 1;
        if (offset != null) {
            pageNo = (offset / limit + 1);
        }
        PageInfo<ProjectModleVO> pageInfo = modleService.queryModleList(pageNo, limit, projectId, modleName);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("rows", pageInfo.getList());
        result.put("total", pageInfo.getTotal());
        result.put("projectId",projectId);
        return result;
    }

    @RequestMapping(value = "/editModle", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView editModle(Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        ProjectModleVO projectModleVO = modleService.findProjectModleVO(id);
        List<VersionVO> list = versionService.queryVersion(null, null, projectModleVO.getModleId()).getList();
        modelAndView.addObject("projectModleVO", projectModleVO);
        modelAndView.addObject("versionVOList", list);
        modelAndView.setViewName("modle/editModle");
        return modelAndView;
    }

    @RequestMapping(value = "/modleList", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView modleList(Integer projectId) {
        ModelAndView modelAndView = new ModelAndView();
        List<Project> list = projectService.findProjectListPage(null, null, null, null, null).getList();
        modelAndView.addObject("list", list);
        modelAndView.addObject("projectId",projectId);
        modelAndView.setViewName("modle/modleList");
        return modelAndView;
    }

    @RequestMapping(value = "/saveModle", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView saveModle(Integer projectId) {
        ModelAndView mav = getModelAndView();
        mav.setViewName("modle/addModle");
        String[] mpid = getRequest().getParameterValues("mpid");
        String[] modleIds = getRequest().getParameterValues("modleId");
        String[] version = getRequest().getParameterValues("version");
        List<ModleVersionVO> modlePrototypeList = modleService.findModlePrototypeList(projectId);
        if (modlePrototypeList != null && !modlePrototypeList.isEmpty()) {
            mav.addObject("list", modlePrototypeList);
            mav.addObject("projectId", projectId);
        }
        if (mpid == null || mpid.length == 0) {
            mav.addObject("msg", "请选择系统模块");
            return mav;
        }
        Integer j;
        for (int i = 0; i < mpid.length; i++) {
            j = Integer.valueOf(mpid[i]);
            if (StringUtils.isEmpty(version[j])) {
                mav.addObject("msg", "选中模块请选择版本号");
                return mav;
            }
        }
        projectService.saveModle(projectId, modleIds, version, mpid);
        List<Project> list = projectService.findProjectListPage(null, null, null, null, null).getList();
        Project project = projectService.queryProject(projectId);
        mav.addObject("list", list);
        mav.addObject("project", project);
        mav.setViewName("modle/modleList");
        return mav;
    }


    @RequestMapping(value = "/addModle", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView addModle(Integer projectId) {
        ModelAndView mav = getModelAndView();
        mav.setViewName("modle/addModle");
        List<ModleVersionVO> modlePrototypeList = modleService.findModlePrototypeList(projectId);
        if (modlePrototypeList != null && !modlePrototypeList.isEmpty()) {
            mav.addObject("list", modlePrototypeList);
            mav.addObject("projectId", projectId);
            return mav;
        }
        mav.addObject("msg", "模块原型已全部添加至项目模块中。如还想添加，请先添加模块到模块原型中！！！");
        return mav;
    }

    @RequestMapping(value = "/deleteModle", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView deleteModle(Integer id, Integer projectId) {
        ModelAndView mav = new ModelAndView();
        modleService.deleteModle(id);
        List<Project> list = projectService.findProjectListPage(null, null, null, null, null).getList();
        mav.addObject("list", list);
        mav.addObject("projectId", projectId);
        mav.setViewName("modle/modleList");
        return mav;
    }

    @RequestMapping(value = "/updateModle", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView updateModle(ProjectModleVO projectModleVO) {
        ModelAndView mav = new ModelAndView();

        ModlePrototype modlePrototype = new ModlePrototype();
        modlePrototype.setId(projectModleVO.getModleId());
        modlePrototype.setModlePrototypeName(projectModleVO.getModleName());
        modlePrototype.setModlePrototypeDescribe(projectModleVO.getModleDescribe());
        modlePrototypeService.saveModlePrototype(modlePrototype);

        ProjectModle projectModle = new ProjectModle();
        projectModle.setId(projectModleVO.getId());
        projectModle.setProjectId(projectModleVO.getProjectId());
        projectModle.setModleId(projectModleVO.getModleId());
        projectModle.setVersion(projectModleVO.getVersion());
        modleService.updateModle(projectModle);

        List<Project> list = projectService.findProjectListPage(null, null, null, null, null).getList();
        Project project = projectService.queryProject(projectModleVO.getProjectId());
        mav.addObject("list", list);
        mav.addObject("project", project);
        mav.setViewName("modle/modleList");
        return mav;
    }

}
