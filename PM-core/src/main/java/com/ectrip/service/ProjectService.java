package com.ectrip.service;

import com.ectrip.model.Project;
import com.ectrip.vo.OptRecordAndEnvVO;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by huangxinguang on 2017/4/20 下午2:18.
 * </p>
 * Desc:
 */
public interface ProjectService {

    void saveProject(Integer id,String projectName, String projectLeader, String phone, String QQ, String email, String projectStatus);

    /**
     * 按条件查询操作记录和环境
     *
     * @param pageNo   当前页号
     * @param pageSize 页面大小
     * @return
     */
    PageInfo<Project> findProjectListPage(Integer pageNo, Integer pageSize, String projectStatus, String projectName, String projectLeader);
}
