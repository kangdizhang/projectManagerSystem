package com.ectrip.service;

import com.ectrip.vo.DemandVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/5/19 0019.
 */
public interface DemandService {

    /**
     * 分页条件查询需求
     * @param pageNo
     * @param pageSize
     * @param projectId
     * @param demandName
     * @param demandStatus
     * @return
     */
    PageInfo<DemandVO> queryDemand(Integer pageNo, Integer pageSize, Integer projectId, String demandName, String demandStatus);
}
