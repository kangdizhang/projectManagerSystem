package com.ectrip.service.impl;

import com.ectrip.dao.ModleDAO;
import com.ectrip.dao.ModlePrototypeDAO;
import com.ectrip.model.Demand;
import com.ectrip.model.Modle;
import com.ectrip.model.ModlePrototype;
import com.ectrip.service.ModleService;
import com.ectrip.vo.ModleVO;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2017/5/16 0016.
 */
@Service
public class ModleServiceImpl implements ModleService {
    private Logger logger = LoggerFactory.getLogger(ModleServiceImpl.class);

    @Autowired
    private ModleDAO modleDAO;

    @Autowired
    private ModlePrototypeDAO modlePrototypeDAO;

    /**
     * 根据需求ID查询关联模块列表
     * @param demandId
     * @return list
     */
    public List<Modle> findModleList(Integer demandId){
        return modleDAO.findModleList(demandId);
    }

    /**
     * 根据ID删除指定项目模块
     * @param id
     */
    public void deleteModle(Integer id){
        modleDAO.deleteModle(id);
    }

    /**
     * 新增项目模块
     * @param projectId
     * @param modlePrototypeId
     * @return int
     */
    public int saveModle(Integer projectId,Integer modlePrototypeId){
        ModlePrototype modlePrototype = modlePrototypeDAO.findModlePrototype(modlePrototypeId);
        Modle modle = new Modle();
        modle.setModleName(modlePrototype.getModlePrototypeName());
        modle.setProjectId(projectId);
        modle.setModleDescribe(modlePrototype.getModlePrototypeDescribe());
        modle.setModleState("0");
        return modleDAO.saveModle(modle);
    }

    /**
     * 修改项目模块
     * @param modle
     * @return int
     */
    public int updateModle(Modle modle){
        return modleDAO.updateModle(modle);
    }

    /**
     * 项目模块分页条件查询
     * @param pageNo
     * @param pageSize
     * @param projectId
     * @param modleName
     * @param modleState
     * @return pageInfo
     */
    public PageInfo<ModleVO> queryModleList(Integer pageNo, Integer pageSize, Integer projectId, String modleName, String modleState){
        List<ModleVO> list = modleDAO.queryModle(pageNo,pageSize,projectId,modleName,modleState);
        logger.info("查询数据:{}",list.toString());
        return new PageInfo<>(list);
    }

    /**
     * 获取未选中的模块原型列表
     * @param projectId
     * @return list
     */
    public List<ModlePrototype> findModlePrototypeList(Integer projectId){

        List<Modle> modleList = modleDAO.queryModleList(projectId);

        List<ModlePrototype> modlePrototypeList = modlePrototypeDAO.queryModlePrototype();
        Iterator<ModlePrototype> modlePrototypeIterator = modlePrototypeList.iterator();

        if(modleList != null && !modleList.isEmpty()){
            while (modlePrototypeIterator.hasNext()){
                ModlePrototype modlePrototype = modlePrototypeIterator.next();
                for (Modle modle:modleList) {
                    if (modle.getModleName().equals(modlePrototype.getModlePrototypeName())){
                        modlePrototypeIterator.remove();
                    }
                }
            }
        }

        return modlePrototypeList;
    }

    @Override
    public List<Modle> queryModleListByProjectId(Integer projectId) {
        return modleDAO.queryModleListByProjectId(projectId);
    }
}
