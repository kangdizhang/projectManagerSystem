package com.ectrip.model;

/**
 * Created by Administrator on 2017/5/9 0009.
 * 项目实体类
 */
public class Project {

    private Integer id;//自增主键
    private String projectName;//项目名称
    private String projectLeader;//项目负责人
    private String phone;//负责人电话
    private String QQ;//负责人QQ
    private String email;//负责人邮箱
    private String operateTime;//操作时间
    private Integer status;//项目状态

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectLeader() {
        return projectLeader;
    }

    public void setProjectLeader(String projectLeader) {
        this.projectLeader = projectLeader;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Project() {
    }

    public Project(Integer id, String projectName, String projectLeader, String phone, String QQ, String email, String operateTime) {
        this.id = id;
        this.projectName = projectName;
        this.projectLeader = projectLeader;
        this.phone = phone;
        this.QQ = QQ;
        this.email = email;
        this.operateTime = operateTime;
    }
}
