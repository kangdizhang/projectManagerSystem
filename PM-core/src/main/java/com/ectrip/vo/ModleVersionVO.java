package com.ectrip.vo;

import java.util.List;

/**
 * Created by 23626 on 2017/5/25.
 */
public class ModleVersionVO {

    private Integer id;//自增主键

    private String modlePrototypeName;//模块原型名称
    private String modlePrototypeDescribe;
    private List<VersionVO> versionVOList;

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

    public List<VersionVO> getVersionVOList() {
        return versionVOList;
    }

    public void setVersionVOList(List<VersionVO> versionVOList) {
        this.versionVOList = versionVOList;
    }
}
