package com.ectrip.model;

/**
 * Created by Administrator on 2017/5/9 0009.
 * 用户实体类
 */
public class User {

    private Integer id;//自增主键
    private String userName;//用户名
    private String pwd;//密码
    private String userType;//用户类型
    private String createTime;//创建时间
    private String lastLoginTime;//最后登录时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public User() {
    }
}
