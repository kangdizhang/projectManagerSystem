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
     * 修改需求
     * @param id
     */
    @Transactional
    public void updateDemand(Integer id, String userName, String versionDesc){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DemandVO demandVO = demandDAO.findDemand(id);//主键查询需求
        demandVO.setCompleteUserId(userName);
        demandVO.setDemandStatus("1");
        demandVO.setActualEndTime(sdf.format(new Date()));
        demandDAO.updateDemandState(demandVO);//修改需求状态

        List<ModleDemand> modleDemands = modleDemandDAO.queryModleList(demandVO.getId());
        if(!CollectionUtils.isEmpty(modleDemands)){
            ProjectModle projectModle;
            Version version;
            for(ModleDemand modleDemand:modleDemands){
                projectModle = modleDAO.queryProjectModle(demandVO.getProjectId(),modleDemand.getModleId());
                projectModle.setVersion(demandVO.getVersion());
                modleDAO.updateModle(projectModle);
                version = new Version();
                version.setModleId(modleDemand.getModleId());
                version.setUpTime(sdf.format(new Date()));
                version.setUpUserId(userName);
                version.setVersionDesc(demandVO.getDemandDescribe());
                version.setUpTime(sdf.format(new Date()));
                version.setVersion(demandVO.getVersion());
                version.setDemandId(demandVO.getId());
                versionDAO.saveVersion(version);
            }
        }
    }

    @Transactional
    public void saveDemand(String[] modleId, Demand demand,String userName){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        demand.setPutUserId(userName);
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
            }
        }else{
            if("开发中".equals(demand.getDemandStatus())){
                demand.setDemandStatus("0");
            }else{
                demand.setDemandStatus("1");
            }
            demandDAO.updateDemand(demand);
            modleDemandDAO.deleteModle(demand.getId());
            ModleDemand modleDemand;
            for (int i = 0; i < modleId.length; i++) {
                modleDemand = new ModleDemand();
                modleDemand.setDemandId(demand.getId());
                modleDemand.setModleId(Integer.valueOf(modleId[i]));
                modleDemandDAO.saveModle(modleDemand);
            }

//            List<ProjectModle> modleList = modleDAO.queryModleList(demand.getProjectId());//需求关联模块列表
//            if (!CollectionUtils.isEmpty(modleList)){
//                for (ProjectModle modle:modleList) {
//                    modle.setVersion(demand.getVersion());
//                    modleDAO.updateModle(modle);
//                }
//            }
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
