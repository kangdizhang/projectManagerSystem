package com.ectrip.model;

/**
 * 文件信息实体类
 */
public class FileInfo {
    private Integer id;
    private Integer demandId;
    private String filePath;
    private String upFileName;
    private String saveFileName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDemandId() {
        return demandId;
    }

    public void setDemandId(Integer demandId) {
        this.demandId = demandId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getUpFileName() {
        return upFileName;
    }

    public void setUpFileName(String upFileName) {
        this.upFileName = upFileName;
    }

    public String getSaveFileName() {
        return saveFileName;
    }

    public void setSaveFileName(String saveFileName) {
        this.saveFileName = saveFileName;
    }

    public FileInfo() {
    }
}
