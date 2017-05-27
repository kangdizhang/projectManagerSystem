package com.ectrip.service.impl;

import com.ectrip.dao.VersionDAO;
import com.ectrip.model.Version;
import com.ectrip.service.VersionService;
import com.ectrip.vo.VersionVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/5/22 0022.
 */
@Service
public class VersionServiceImpl implements VersionService {
    @Autowired
    private VersionDAO versionDAO;

    /**
     * 新增版本
     * @param version
     */
    @Transactional
    public void saveVersion(Version version){
        versionDAO.saveVersion(version);
    }

    /**
     * 更新版本
     * @param modleId
     */
    @Transactional
    public void updateVersion(Integer modleId){
        versionDAO.updateVersion(modleId);
    }

    /**
     * 主键查询版本
     * @param id
     * @return version
     */
    public Version findVersion(Integer id){
        return versionDAO.findVersion(id);
    }

    /**
     * 条件查询版本列表
     * @param pageNo
     * @param pageSize
     * @param modleId
     * @return list
     */
    public PageInfo<VersionVO> queryVersion(Integer pageNo, Integer pageSize, Integer modleId){
        List<VersionVO> list = versionDAO.queryVersion(pageNo,pageSize,modleId);
        return new PageInfo<VersionVO>(list);
    }
}
