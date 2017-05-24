package com.ectrip.service;

import com.ectrip.model.ProjectModle;
import com.ectrip.model.ModlePrototype;
import com.ectrip.vo.ProjectModleVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/5/16 0016.
 */
public interface ModleService {

    /**
     * 根据需求ID查询关联模块列表
     * @param demandId
     * @return list
     */
    List<ProjectModle> findModleList(Integer demandId);

    /**
     * 根据ID删除指定项目模块
     * @param id
     */
    void deleteModle(Integer id);

    /**
     * 新增项目模块
     * @param projectId
     * @param modlePrototypeId
     * @return int
     */
    int saveModle(Integer projectId,Integer modlePrototypeId);

    /**
     * 修改项目模块
     * @param modle
     * @return int
     */
    int updateModle(ProjectModle modle);

    /**
     * 项目模块分页条件查询
     * @param pageNo
     * @param pageSize
     * @param projectId
     * @param modleName
     * @param modleState
     * @return pageInfo
     */
    public PageInfo<ProjectModleVO> queryModleList(Integer pageNo, Integer pageSize, Integer projectId, String modleName, String modleState);

    /**
     * 获取未选中的模块原型列表
     * @param projectId
     * @return list
     */
    public List<ModlePrototype> findModlePrototypeList(Integer projectId);

    List<ProjectModle> queryModleListByProjectId(Integer projectId);
}
