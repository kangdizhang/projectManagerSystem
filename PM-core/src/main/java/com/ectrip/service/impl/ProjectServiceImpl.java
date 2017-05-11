package com.ectrip.service.impl;

import com.ectrip.dao.ProjectDao;
import com.ectrip.model.Project;
import com.ectrip.service.ProjectService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public void saveProject(String projectName, String projectLeader, String phone, String QQ, String email, String operateTime, String projectStatus) {
        Project project = new Project();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        project.setProjectName(projectName);
        project.setProjectLeader(projectLeader);
        project.setPhone(phone);
        project.setQQ(QQ);
        project.setEmail(email);
        project.setOperateTime(operateTime);
        project.setProjectStatus(projectStatus);
        project.setOperateTime(sdf.format(new Date()));
        logger.info("保存数据:{}",project.toString());
        projectDao.save(project);
    }

    @Override
    public PageInfo<Project> findProjectListPage(Integer pageNo, Integer pageSize, String projectStatus, String projectName, String projectLeader) {
        List<Project> list = projectDao.findProjectListPage(pageNo,pageSize,projectStatus,projectName,projectLeader);
        logger.info("查询数据:{}",list.toString());
        return new PageInfo<>(list);
    }
}
