package com.ectrip.service;

import com.ectrip.model.Version;
import com.ectrip.vo.VersionVO;
import com.github.pagehelper.PageInfo;

/**
 * Created by Administrator on 2017/5/22 0022.
 */
public interface VersionService {
    /**
     * 新增版本
     * @param version
     */
    void saveVersion(Version version);

    /**
     * 更新版本
     * @param modleId
     */
    void updateVersion(Integer modleId);

    /**
     * 主键查询版本
     * @param id
     * @return version
     */
    Version findVersion(Integer id);

    /**
     * 条件查询版本列表
     * @param pageNo
     * @param pageSize
     * @param modleId
     * @param versionState
     * @return list
     */
    PageInfo<VersionVO> queryVersion(Integer pageNo, Integer pageSize, Integer modleId, String versionState);
}
