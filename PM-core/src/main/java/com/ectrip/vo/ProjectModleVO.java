package com.ectrip.vo;

/**
 * Created by Administrator on 2017/5/19 0019.
 */
public class ProjectModleVO {

    private Integer id;//自增主键
    private Integer projectId;//项目表主键
    private String projectName;//项目名称
    private Integer modleId;//模块ID
    private String modleName;//模块名称
    private String modleDescribe;//模块描述
    private String version;//当前版本版本号

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getModleName() {
        return modleName;
    }

    public void setModleName(String modleName) {
        this.modleName = modleName;
    }

    public String getModleDescribe() {
        return modleDescribe;
    }

    public void setModleDescribe(String modleDescribe) {
        this.modleDescribe = modleDescribe;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getModleId() {
        return modleId;
    }

    public void setModleId(Integer modleId) {
        this.modleId = modleId;
    }

    public ProjectModleVO() {
    }
}
