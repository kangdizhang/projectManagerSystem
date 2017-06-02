package com.ectrip.service.impl;

import com.ectrip.dao.*;
import com.ectrip.model.*;
import com.ectrip.service.ProjectService;
import com.ectrip.vo.ModleVersionVO;
import com.ectrip.vo.ProjectInfoVO;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 23626 on 2017/5/11.
 */
@Service
public class ProjectServiceImpl implements ProjectService {
    private Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);

    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private ProjectInfoDAO projectInfoDAO;

    /**
     * 根据ID删除项目配置信息
     *
     * @param id
     */
    public void delProjectInfo(Integer id) {
        projectInfoDAO.delProjectInfo(id);
    }

    @Autowired
    private ProjectModleDAO projectModleDAO;

    @Autowired
    private VersionDAO versionDAO;

    @Override
    @Transactional
    public void saveProject(Project project, ProjectInfo projectInfo, String[] modleIds, String[] versions, String[] mpid) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        project.setOperateTime(sdf.format(new Date()));
        logger.info("保存数据:{}", project.toString());
        if (project.getId() == null) {
            projectDao.saveProject(project);
            Integer j;
            Version version;
                for (int i = 0; i < mpid.length; i++) {
                    j = Integer.valueOf(mpid[i]);
                    version = versionDAO.findVersion(Integer.parseInt(versions[j]));
                    ProjectModle projectModle = new ProjectModle();
                    projectModle.setProjectId(project.getId());
                    projectModle.setModleId(Integer.parseInt(modleIds[j]));
                    projectModle.setVersion(version.getVersion());
                    projectModleDAO.saveModle(projectModle);
                }
            if (projectInfo != null) {
                projectInfo.setId(null);
                projectInfo.setProjectId(project.getId());
                projectInfoDAO.saveProjectInfo(projectInfo);
            }
        } else {
            projectDao.updateProject(project);
        }
    }


    @Transactional
    public void saveModle(Integer projectId, String[] modleIds, String[] versions, String[] mpid){
        Integer j;
        Version version;
        for (int i = 0; i < mpid.length; i++) {
            j = Integer.valueOf(mpid[i]);
            version = versionDAO.findVersion(Integer.parseInt(versions[j]));
            ProjectModle projectModle = new ProjectModle();
            projectModle.setProjectId(projectId);
            projectModle.setModleId(Integer.parseInt(modleIds[j]));
            projectModle.setVersion(version.getVersion());
            projectModleDAO.saveModle(projectModle);
        }
    }
    @Override
    @Transactional
    public void saveProjectInfo(ProjectInfo projectInfo) {
        if (projectInfo.getId() == null) {
            projectInfoDAO.saveProjectInfo(projectInfo);
        } else {
            projectInfoDAO.updateProjectInfo(projectInfo);
        }
    }

    @Override
    public Project queryProject(Integer projectId) {
        return projectDao.findProject(projectId);
    }

    @Override
    public ProjectInfoVO queryProjectInfo(Integer id) {
        return projectInfoDAO.findProjectInfoByProjectId(id);
    }

    @Override
    public PageInfo<Project> findProjectListPage(Integer pageNo, Integer pageSize, String projectStatus, String projectName, String projectLeader) {
        List<Project> list = projectDao.findProjectListPage(pageNo, pageSize, projectStatus, projectName, projectLeader);
        logger.info("查询数据:{}", list.toString());
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<ProjectInfoVO> findProjectInfoListPage(Integer pageNo, Integer pageSize, String projectName) {
        List<ProjectInfoVO> list = projectInfoDAO.findProjectInfoListPage(pageNo, pageSize, projectName);
        logger.info("查询数据:{}", list.toString());
        return new PageInfo<>(list);
    }

    @Transactional
    @Override
    public void deleteProject(Integer id) {
        Project project = projectDao.findProject(id);
        projectDao.deleteProject(id);
        projectInfoDAO.delProjectInfo(project.getId());
    }


}
