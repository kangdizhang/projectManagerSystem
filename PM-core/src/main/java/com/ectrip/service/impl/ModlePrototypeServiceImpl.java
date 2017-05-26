package com.ectrip.service.impl;

import com.ectrip.dao.ModlePrototypeDAO;
import com.ectrip.dao.ProjectDao;
import com.ectrip.dao.ProjectInfoDAO;
import com.ectrip.dao.VersionDAO;
import com.ectrip.model.ModlePrototype;
import com.ectrip.model.Project;
import com.ectrip.model.ProjectInfo;
import com.ectrip.model.Version;
import com.ectrip.service.ModlePrototypeService;
import com.ectrip.service.ProjectService;
import com.ectrip.vo.ModleVersionVO;
import com.ectrip.vo.ProjectInfoVO;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 23626 on 2017/5/11.
 */
@Service
public class ModlePrototypeServiceImpl implements ModlePrototypeService {

    @Autowired
    ModlePrototypeDAO modlePrototypeDAO;

    @Autowired
    VersionDAO versionDAO;
    private Logger logger = LoggerFactory.getLogger(ModlePrototypeServiceImpl.class);

    @Override
    public PageInfo<ModlePrototype> findModlePrototypeListPage(Integer pageNo, Integer pageSize, String modlePrototypeName) {
        List<ModlePrototype> list = modlePrototypeDAO.findModlePrototypeListPage(pageNo, pageSize, modlePrototypeName);
        logger.info("查询数据:{}", list.toString());
        return new PageInfo<>(list);
    }

    @Override
    public List<ModleVersionVO> queryModlePrototype() {
        List<ModleVersionVO> list = modlePrototypeDAO.queryModlePrototype();
        logger.info("查询数据:{}", list.toString());
        return list;
    }

    @Override
    @Transactional
    public void saveModlePrototype(Integer id, String modlePrototypeName, String modlePrototypeDescribe) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ModlePrototype modlePrototype = new ModlePrototype();
        modlePrototype.setModlePrototypeName(modlePrototypeName);
        modlePrototype.setModlePrototypeDescribe(modlePrototypeDescribe);
        if(id == null){
            modlePrototypeDAO.saveModlePrototype(modlePrototype);
            Version version = new Version();
            version.setUpUserId("testUser");
            version.setVersion("v1.0");
            version.setVersionDesc("初始化");
            version.setUpTime(sdf.format(new Date()));
            version.setModleId(modlePrototype.getId());
            versionDAO.saveVersion(version);
        }else{
            modlePrototype.setId(id);
            modlePrototypeDAO.updateModlePrototype(modlePrototype);
        }
    }

    @Override
    public ModleVersionVO findModlePrototype(Integer id) {
        return modlePrototypeDAO.findModlePrototype(id);
    }
}
