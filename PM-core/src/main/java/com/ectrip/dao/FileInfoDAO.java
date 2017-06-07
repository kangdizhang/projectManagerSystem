package com.ectrip.dao;

import com.ectrip.model.FileInfo;

public interface FileInfoDAO {

    /**
     * 需求ID查询文件信息
     * @param demandId
     * @return
     */
    FileInfo findFileInfoById(Integer demandId);

    /**
     * 保存文件信息
     * @param fileInfo
     */
    void saveFileInfo(FileInfo fileInfo);
}
