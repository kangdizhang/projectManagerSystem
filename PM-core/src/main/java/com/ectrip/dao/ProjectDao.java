package com.ectrip.dao;

import com.ectrip.model.OptRecord;
import com.ectrip.model.Project;
import com.ectrip.vo.ProjectInfoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 23626 on 2017/5/11.
 */
public interface ProjectDao {

    Project findProject(@Param("projectId") Integer projectId);

    void save(Project project);

    void updateProject(Project project);

    List<Project> findProjectListPage(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize,@Param("projectStatus") String projectStatus, @Param("projectName") String projectName, @Param("projectLeader") String projectLeader);

    /**
     * 根据ID删除指定项目
     * @param id
     */
    void deleteProject(@Param("id")Integer id);
}
