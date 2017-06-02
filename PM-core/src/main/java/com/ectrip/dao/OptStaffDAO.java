package com.ectrip.dao;

import com.ectrip.model.OptStaff;
import com.ectrip.vo.OptStaffVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OptStaffDAO {

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
    List<OptStaffVO> queryOptStaffByPageList(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize,
                                             @Param("projectId") Integer projectId, @Param("optStaffName") String optStaffName);
}
