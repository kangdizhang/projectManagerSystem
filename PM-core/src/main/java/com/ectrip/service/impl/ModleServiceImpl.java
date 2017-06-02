package com.ectrip.service.impl;

import com.ectrip.dao.ModleDemandDAO;
import com.ectrip.dao.ProjectModleDAO;
import com.ectrip.dao.ModlePrototypeDAO;
import com.ectrip.dao.VersionDAO;
import com.ectrip.model.ModleDemand;
import com.ectrip.model.ProjectModle;
import com.ectrip.model.ModlePrototype;
import com.ectrip.model.Version;
import com.ectrip.service.ModleService;
import com.ectrip.vo.ModleVersionVO;
import com.ectrip.vo.ProjectModleVO;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2017/5/16 0016.
 */
@Service
public class ModleServiceImpl implements ModleService {
    private Logger logger = LoggerFactory.getLogger(ModleServiceImpl.class);

    @Autowired
    private ProjectModleDAO modleDAO;

    @Autowired
    private ModlePrototypeDAO modlePrototypeDAO;

    @Autowired
    private VersionDAO versionDAO;

    @Autowired
    private ModleDemandDAO modleDemandDAO;

    @Autowired
    private ProjectModleDAO projectModleDAO;

    /**
     * 根据需求ID查询关联模块列表
     * @param demandId
     * @return list
     */
    public List<ProjectModleVO> findModleList(Integer demandId){
        return projectModleDAO.findModleList(demandId);
    }

    /**
     * 主键查询项目模块
     * @param id
     * @return
     */
    public ProjectModleVO findProjectModleVO(Integer id){
        return modleDAO.findProjectModleVO(id);
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
        ModleVersionVO modlePrototype = modlePrototypeDAO.findModlePrototype(modlePrototypeId);
        ProjectModle modle = new ProjectModle();
        modle.setModleId(modlePrototype.getId());
        modle.setProjectId(projectId);
        return modleDAO.saveModle(modle);
    }

    /**
     * 修改项目模块
     * @param modle
     * @return int
     */
    public int updateModle(ProjectModle modle){
        return modleDAO.updateModle(modle);
    }

    /**
     * 项目模块分页条件查询
     * @param pageNo
     * @param pageSize
     * @param projectId
     * @param modleName
     */
    public PageInfo<ProjectModleVO> queryModleList(Integer pageNo, Integer pageSize, Integer projectId, String modleName){
        List<ProjectModleVO> list = modleDAO.queryModle(pageNo,pageSize,projectId,modleName);
        logger.info("查询数据:{}",list.toString());
        return new PageInfo<>(list);
    }

    /**
     * 获取未选中的模块原型列表
     * @param projectId
     * @return list
     */
    public List<ModleVersionVO> findModlePrototypeList(Integer projectId){

        List<ProjectModle> modleList = modleDAO.queryModleList(projectId);

        List<ModleVersionVO> modlePrototypeList = modlePrototypeDAO.queryModlePrototype();
        Iterator<ModleVersionVO> modlePrototypeIterator = modlePrototypeList.iterator();

        if(modleList != null && !modleList.isEmpty()){
            while (modlePrototypeIterator.hasNext()){
                ModleVersionVO modleVersionVO= modlePrototypeIterator.next();
                for (ProjectModle modle:modleList) {
                    if (modle.getModleId()==modleVersionVO.getId()){
                        modlePrototypeIterator.remove();
                    }
                }
            }
        }
        return modlePrototypeList;
    }

    @Override
    public List<ProjectModleVO> queryModleListByProjectId(Integer projectId) {
        return modleDAO.queryModle(null,null,projectId,null);
    }
}
