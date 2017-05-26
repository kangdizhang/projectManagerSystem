package com.ectrip.dao;

import com.ectrip.model.ProjectModle;
import com.ectrip.vo.ProjectModleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/11 0011.
 */
public interface ProjectModleDAO {

    /**
     * 修改指定需求关联模块未完成状态为已完成
     * @param demandId
     */
    //void updateModleState(Integer demandId);

    /**
     * 根据需求ID查询关联模块列表
     * @param demandId
     * @return list
     */
    //List<ProjectModle> findModleList(Integer demandId);

    /**
     * 根据ID删除指定项目模块
     * @param id
     */
    void deleteModle(Integer id);

    /**
     * 新增模块
     * @param modle
     * @return int
     */
    int saveModle(ProjectModle modle);

    /**
     * 更新模块
     * @param modle
     * @return int
     */
    int updateModle(ProjectModle modle);

    /**
     * 主键查询项目模块
     * @param id
     * @return
     */
    ProjectModleVO findProjectModleVO(@Param("id") Integer id);

    /**
     * 查找指定项目的模块列表
     * @param projectId
     * @return list
     */
    List<ProjectModle> queryModleList(Integer projectId);

    /**
     * 条件查询
     * @param projectId
     * @param modleName
     * @return list
     */
    List<ProjectModleVO> queryModle(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("projectId") Integer projectId,
                                    @Param("modleName") String modleName);

    //List<ProjectModle> queryModleListByProjectId(@Param("projectId") Integer projectId);

    //void updateModleDev(@Param("id")Integer id);
}
