package com.ectrip.dao;

import com.ectrip.vo.DemandVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
public interface DemandVODAO {

    /**
     * 分页条件查询需求
     * @param pageNo
     * @param pageSize
     * @param projectId
     * @param demandName
     * @param demandStatus
     * @return
     */
    List<DemandVO> queryDemand(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize,
                               @Param("projectId") Integer projectId, @Param("demandName") String demandName, @Param("demandStatus") String demandStatus);
}
