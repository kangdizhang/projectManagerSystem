package com.ectrip.dao;

import com.ectrip.model.Demand;
import com.ectrip.vo.DemandVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/5/22 0022.
 */
public interface DemandDAO {

    /**
     * 修改需求状态
     * @param demandVO
     */
    void updateDemandState(DemandVO demandVO);

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
    List<DemandVO> queryDemand(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("projectId") Integer projectId,
                               @Param("demandName") String demandName, @Param("demandStatus") String demandStatus);

    void saveDemand(Demand demand);

    void updateDemand(Demand demand);

    List<Demand> queryDemandByVeision(@Param("version")String version,@Param("projectId") Integer projectId,@Param("id") Integer id);

    void deleteDemand(@Param("id")Integer id);
}
