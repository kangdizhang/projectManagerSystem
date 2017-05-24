package com.ectrip.model;

/**
 * Created by Administrator on 2017/5/9 0009.
 * 项目模块实体类
 */
public class ProjectModle {

    private Integer id;//自增主键
    private Integer projectId;//项目表主键
    private Integer modleId;//模块ID
    private String version;//版本号

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

    public Integer getModleId() {
        return modleId;
    }

    public void setModleName(Integer modleId) {
        this.modleId = modleId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public ProjectModle() {
    }
}
