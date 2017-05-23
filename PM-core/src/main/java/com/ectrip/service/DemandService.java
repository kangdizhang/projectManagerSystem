package com.ectrip.service;

import com.ectrip.model.Demand;
import com.ectrip.vo.DemandVO;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/5/19 0019.
 */
public interface DemandService {

    /**
     * 修改需求状态
     * @param id
     */
    public void updateDemand(Integer id);

    /**
     * 主键查询
     * @param id
     * @return demandVO
     */
    DemandVO findDemand(Integer id);

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

    void saveDemand(String[] modleId, Demand demand);

    List<Demand> queryDemandByVeision(String version,Integer projectId, Integer id);

    void deleteDemand(Integer id);
}
