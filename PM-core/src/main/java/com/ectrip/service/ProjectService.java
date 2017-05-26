package com.ectrip.service;

import com.ectrip.model.Project;
import com.ectrip.model.ProjectInfo;
import com.ectrip.vo.ProjectInfoVO;
import com.github.pagehelper.PageInfo;

/**
 * Created by huangxinguang on 2017/4/20 下午2:18.
 * </p>
 * Desc:
 */
public interface ProjectService {

    void saveProject(Project project,ProjectInfo projectInfo,String[] modleIds,String[] version,String[] mpid);

    /**
     * 根据ID删除项目配置信息
     * @param id
     */
    public void delProjectInfo(Integer id);

    void saveProjectInfo(ProjectInfo projectInfo);
    Project queryProject(Integer projectId);
    ProjectInfoVO queryProjectInfo(Integer id);
    /**
     * 按条件查询操作记录和环境
     *
     * @param pageNo   当前页号
     * @param pageSize 页面大小
     * @return
     */
    PageInfo<Project> findProjectListPage(Integer pageNo, Integer pageSize, String projectStatus, String projectName, String projectLeader);

    PageInfo<ProjectInfoVO> findProjectInfoListPage(Integer pageNo, Integer pageSize, String projectName);

    /**
     * 根据ID删除指定项目
     * @param id
     */
    void deleteProject(Integer id);
}
