package com.ectrip.vo;

/**
 * Created by Administrator on 2017/5/22 0022.
 */
public class VersionVO {

    private Integer id;//自增主键
    private String version;//申请的版本号
    private Integer modleId;//模块主键，关联模块
    private String modleName;//关联模块名
    private String upUserId;//升级人
    private String upTime;//升级时间
    private Integer versionId;//升级前的版本ID
    private String versionNum;//升级前的版本号
    private Integer demandId;//升级对应的需求ID
    private String demandName;//升级对应的需求名
    private Integer versionState;//版本状态，0：停用，1：启用

    public VersionVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getModleName() {
        return modleName;
    }

    public void setModleName(String modleName) {
        this.modleName = modleName;
    }

    public String getUpUserId() {
        return upUserId;
    }

    public void setUpUserId(String upUserId) {
        this.upUserId = upUserId;
    }

    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime;
    }

    public Integer getVersionId() {
        return versionId;
    }

    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }

    public String getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(String versionNum) {
        this.versionNum = versionNum;
    }

    public Integer getDemandId() {
        return demandId;
    }

    public void setDemandId(Integer demandId) {
        this.demandId = demandId;
    }

    public String getDemandName() {
        return demandName;
    }

    public void setDemandName(String demandName) {
        this.demandName = demandName;
    }

    public Integer getVersionState() {
        return versionState;
    }

    public void setVersionState(Integer versionState) {
        this.versionState = versionState;
    }
}
