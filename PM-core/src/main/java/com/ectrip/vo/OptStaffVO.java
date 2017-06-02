package com.ectrip.vo;

public class OptStaffVO {

    private Integer id;
    private Integer projectId;
    private String projectName;
    private String optStaffName;
    private String tel;
    private String qq;
    private String email;

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

    public String getOptStaffName() {
        return optStaffName;
    }

    public void setOptStaffName(String optStaffName) {
        this.optStaffName = optStaffName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public OptStaffVO() {
    }
}
