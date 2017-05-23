package com.ectrip.service.impl;


import com.ectrip.dao.DemandDAO;
import com.ectrip.model.Demand;
import com.ectrip.service.DemandService;
import com.ectrip.vo.DemandVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/19 0019.
 */
@Service
public class DemandServiceImpl implements DemandService {

    @Autowired
    private DemandDAO demandDAO;

    /**
     * 主键查询
     * @param id
     * @return demandVO
     */
    public DemandVO findDemand(Integer id){
        return demandDAO.findDemand(id);
    }

    /**
     * 分页条件查询需求
     * @param pageNo
     * @param pageSize
     * @param projectId
     * @param demandName
     * @param demandStatus
     * @return
     */
    public PageInfo<DemandVO> queryDemand(Integer pageNo, Integer pageSize, Integer projectId, String demandName, String demandStatus){
        List<DemandVO> list = demandDAO.queryDemand(pageNo,pageSize,projectId,demandName,demandStatus);
        return new PageInfo<DemandVO>(list);
    }

    public void saveDemand(String[] modleId, Demand demand){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        demand.setPutTime(sdf.format(new Date()));
        demand.setPutUserId("test");
        if(demand.getId()==null){
            demand.setDemandStatus("0");
            demandDAO.saveDemand(demand);
        }else{
            demandDAO.updateDemand(demand);
        }
    }
}
