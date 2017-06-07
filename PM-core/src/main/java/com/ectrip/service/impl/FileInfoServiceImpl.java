package com.ectrip.service.impl;

import com.ectrip.dao.FileInfoDAO;
import com.ectrip.model.FileInfo;
import com.ectrip.service.FileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 服务接口实现
 */
@Service
public class FileInfoServiceImpl implements FileInfoService {
    @Autowired
    private FileInfoDAO fileInfoDAO;

    /**
     * 需求ID查询文件信息
     * @param demandId
     * @return
     */
    public FileInfo findFileInfoById(Integer demandId){
        return fileInfoDAO.findFileInfoById(demandId);
    }

    /**
     * 保存文件信息
     * @param fileInfo
     */
    public void saveFileInfo(FileInfo fileInfo){
        fileInfoDAO.saveFileInfo(fileInfo);
    }
}
