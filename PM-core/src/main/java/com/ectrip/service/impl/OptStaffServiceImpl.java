package com.ectrip.service.impl;

import com.ectrip.dao.OptStaffDAO;
import com.ectrip.model.OptStaff;
import com.ectrip.service.OptStaffService;
import com.ectrip.vo.OptStaffVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OptStaffServiceImpl implements OptStaffService {

    @Autowired
    private OptStaffDAO optStaffDAO;

    /**
     * 保存运维人员
     * @param optStaff
     */
    public void saveOptStaff(OptStaff optStaff){
        optStaffDAO.saveOptStaff(optStaff);
    }

    /**
     * 主键删除
     * @param id
     */
    public void delOptStaffById(Integer id){
        optStaffDAO.delOptStaffById(id);
    }

    /**
     * 修改运维人员信息
     * @param optStaff
     */
    public void updateOptStaff(OptStaff optStaff){
        optStaffDAO.updateOptStaff(optStaff);
    }

    /**
     * 主键查询运维人员信息
     * @param id
     */
    public OptStaff findOptStaffById(Integer id){
        return optStaffDAO.findOptStaffById(id);
    }

    /**
     * 分页查询运维人员信息列表
     * @param pageNo
     * @param pageSize
     * @param projectId
     * @param optStaffName
     * @return
     */
    public PageInfo<OptStaffVO> queryOptStaffByPageList(Integer pageNo, Integer pageSize, Integer projectId, String optStaffName){
        return new PageInfo<OptStaffVO>(optStaffDAO.queryOptStaffByPageList(pageNo,pageSize,projectId,optStaffName));
    }

}
