package com.ectrip.service.impl;

import com.ectrip.dao.ProjectModleDAO;
import com.ectrip.dao.ModlePrototypeDAO;
import com.ectrip.dao.ProjectDao;
import com.ectrip.dao.ProjectInfoDAO;
import com.ectrip.model.ProjectModle;
import com.ectrip.model.ModlePrototype;
import com.ectrip.model.Project;
import com.ectrip.model.ProjectInfo;
import com.ectrip.service.ProjectService;
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
    @Autowired
    private ProjectModleDAO modleDAO;
    @Autowired
    private ModlePrototypeDAO modlePrototypeDAO;


    @Override
    @Transactional
    public void saveProject(String[] a,Integer id,String projectName, String projectLeader, String phone, String QQ, String email,  String projectStatus) {
        Project project = new Project();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        project.setProjectName(projectName);
        project.setProjectLeader(projectLeader);
        project.setPhone(phone);
        project.setQq(QQ);
        project.setEmail(email);
        project.setProjectStatus(projectStatus);
        project.setOperateTime(sdf.format(new Date()));
        logger.info("保存数据:{}",project.toString());
        if(id==null){
            projectDao.save(project);
            if (a != null && a.length > 0){
                for (int i = 0; i < a.length; i++){
                    ModlePrototype modlePrototype = modlePrototypeDAO.findModlePrototype(Integer.parseInt(a[i]));
                    ProjectModle modle = new ProjectModle();
                    modle.setProjectId(project.getId());
                    modle.setModleId(modlePrototype.getId());
                    modleDAO.saveModle(modle);
                }
            }
        }else{
            project.setId(id);
            projectDao.updateProject(project);
        }
    }

    @Override
    public void saveProjectInfo(Integer id, Integer projectId, String serverIp, String dbServerIp, String dbUserId, String dbPwd, Integer dbPort, String hostName, String SSH) {
        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setProjectId(projectId);
        projectInfo.setServerIp(serverIp);
        projectInfo.setDbServerIp(dbServerIp);
        projectInfo.setDbUser(dbUserId);
        projectInfo.setDbPwd(dbPwd);
        projectInfo.setDbPort(dbPort);
        projectInfo.setHostName(hostName);
        projectInfo.setSsh(SSH);
        if(id==null){
            projectInfoDAO.saveProjectInfo(projectInfo);
        }else{
            projectInfo.setId(id);
            projectInfoDAO.updateProjectInfo(projectInfo);
        }
    }

    @Override
    public Project queryProject(Integer projectId) {
        return projectDao.findProject(projectId);
    }

    @Override
    public ProjectInfo queryProjectInfo(Integer projectId) {
        return projectInfoDAO.findProjectInfoByProjectId(projectId);
    }

    @Override
    public PageInfo<Project> findProjectListPage(Integer pageNo, Integer pageSize, String projectStatus, String projectName, String projectLeader) {
        List<Project> list = projectDao.findProjectListPage(pageNo,pageSize,projectStatus,projectName,projectLeader);
        logger.info("查询数据:{}",list.toString());
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<ProjectInfoVO> findProjectInfoListPage(Integer pageNo, Integer pageSize, String projectName) {
        List<ProjectInfoVO> list = projectInfoDAO.findProjectInfoListPage(pageNo,pageSize,projectName);
        logger.info("查询数据:{}",list.toString());
        return new PageInfo<>(list);
    }

    @Override
    public void deleteProject(Integer id) {
        projectDao.deleteProject(id);
    }


}
