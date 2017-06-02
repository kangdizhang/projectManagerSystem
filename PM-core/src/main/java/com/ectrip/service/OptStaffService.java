package com.ectrip.service;

import com.ectrip.model.OptStaff;
import com.ectrip.vo.OptStaffVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface OptStaffService {

    /**
     * 保存运维人员
     * @param optStaff
     */
    void saveOptStaff(OptStaff optStaff);

    /**
     * 主键删除
     * @param id
     */
    void delOptStaffById(Integer id);

    /**
     * 修改运维人员信息
     * @param optStaff
     */
    void updateOptStaff(OptStaff optStaff);

    /**
     * 主键查询运维人员信息
     * @param id
     */
    OptStaff findOptStaffById(Integer id);

    /**
     * 分页查询运维人员信息列表
     * @param pageNo
     * @param pageSize
     * @param projectId
     * @param optStaffName
     * @return
     */
    PageInfo<OptStaffVO> queryOptStaffByPageList(Integer pageNo, Integer pageSize, Integer projectId, String optStaffName);
}
