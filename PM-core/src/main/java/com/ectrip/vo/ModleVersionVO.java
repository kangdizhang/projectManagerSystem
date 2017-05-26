package com.ectrip.vo;

import com.ectrip.model.Version;

import java.util.List;

/**
 * Created by 23626 on 2017/5/25.
 */
public class ModleVersionVO {

    private Integer id;//自增主键

    private String modlePrototypeName;//模块原型名称
    private String modlePrototypeDescribe;
    private List<Version> versionList;

    public List<Version> getVersionList() {
        return versionList;
    }

    public void setVersionList(List<Version> versionList) {
        this.versionList = versionList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModlePrototypeDescribe() {
        return modlePrototypeDescribe;
    }

    public void setModlePrototypeDescribe(String modlePrototypeDescribe) {
        this.modlePrototypeDescribe = modlePrototypeDescribe;
    }

    public String getModlePrototypeName() {
        return modlePrototypeName;
    }

    public void setModlePrototypeName(String modlePrototypeName) {
        this.modlePrototypeName = modlePrototypeName;
    }
}
