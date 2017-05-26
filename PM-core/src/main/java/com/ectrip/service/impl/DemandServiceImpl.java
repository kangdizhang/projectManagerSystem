package com.ectrip.service.impl;


import com.ectrip.dao.DemandDAO;
import com.ectrip.dao.ProjectModleDAO;
import com.ectrip.dao.ModleDemandDAO;
import com.ectrip.dao.VersionDAO;
import com.ectrip.model.Demand;
import com.ectrip.model.ProjectModle;
import com.ectrip.model.ModleDemand;
import com.ectrip.model.Version;
import com.ectrip.service.DemandService;
import com.ectrip.vo.DemandVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/19 0019.
 */
@Service
public class DemandServiceImpl implements DemandService {

    @Autowired
    private DemandDAO demandDAO;

    @Autowired
    private ModleDemandDAO modleDemandDAO;

    @Autowired
    private ProjectModleDAO modleDAO;

    @Autowired
    private VersionDAO versionDAO;

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

    /**
     * 修改需求状态
     * @param id
     */
    @Transactional
    public void updateDemand(Integer id){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DemandVO demandVO = demandDAO.findDemand(id);//主键查询需求
        demandVO.setCompleteUserId("test");
        demandVO.setDemandStatus("1");
        demandVO.setActualEndTime(sdf.format(new Date()));
        demandDAO.updateDemandState(demandVO);//修改需求状态
        //modleDAO.updateModleState(id);//修改需求关联模块未完成状态为已完成

        //升级版本
        List<ProjectModle> list = new ArrayList<>();//modleDAO.findModleList(id);//需求关联模块列表

        Version version ;
        for (ProjectModle modle:list) {
            versionDAO.updateVersion(modle.getId());
            version = new Version();
            version.setModleId(modle.getId());
//            version.setDemandId(id);
            version.setUpTime(sdf.format(new Date()));
            version.setUpUserId("test");
            version.setVersion(demandVO.getVersion());
            versionDAO.saveVersion(version);
        }
    }

    public void saveDemand(String[] modleId, Demand demand){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        demand.setPutUserId("test");
        if(demand.getId()==null){
            demand.setPutTime(sdf.format(new Date()));
            demand.setDemandStatus("0");
            demandDAO.saveDemand(demand);
            ModleDemand modleDemand;
            for (int i = 0; i < modleId.length; i++) {
                modleDemand = new ModleDemand();
                modleDemand.setDemandId(demand.getId());
                modleDemand.setModleId(Integer.valueOf(modleId[i]));
                modleDemandDAO.saveModle(modleDemand);
                //modleDAO.updateModleDev(Integer.valueOf(modleId[i]));
            }
        }else{
            demandDAO.updateDemand(demand);
            modleDemandDAO.deleteModle(demand.getId());

            List<ProjectModle> modleList = new ArrayList<>();//modleDAO.findModleList(demand.getId());
            if (!CollectionUtils.isEmpty(modleList)){
                for (ProjectModle modle:modleList) {
                    //modleDAO.updateModleDev(modle.getId());
                }
            }

            ModleDemand modleDemand;
            for (int i = 0; i < modleId.length; i++) {
                modleDemand = new ModleDemand();
                modleDemand.setDemandId(demand.getId());
                modleDemand.setModleId(Integer.valueOf(modleId[i]));

                modleDemandDAO.saveModle(modleDemand);
                //modleDAO.updateModleDev(Integer.valueOf(modleId[i]));
            }
        }
    }

    @Override
    public List<Demand> queryDemandByVeision(String version,Integer projectId,Integer id) {
        return demandDAO.queryDemandByVeision(version,projectId,id);
    }

    @Override
    public void deleteDemand(Integer id){
        //modleDAO.updateModleState(id);
        modleDemandDAO.deleteModle(id);
        demandDAO.deleteDemand(id);
    }
}
