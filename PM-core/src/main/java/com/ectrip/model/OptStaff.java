package com.ectrip.model;

/**
 * 运维人员实体类
 */
public class OptStaff {
    private Integer id;//自增主键
    private Integer projectId;//项目ID
    private String optStaffName;//运维人员名字
    private String tel;//手机号
    private String qq;//QQ
    private String email;//邮箱

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

    public OptStaff() {
    }
}
