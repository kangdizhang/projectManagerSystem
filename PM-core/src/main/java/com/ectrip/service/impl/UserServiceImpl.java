package com.ectrip.service.impl;

import com.ectrip.dao.UserDAO;
import com.ectrip.model.User;
import com.ectrip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/31 0031.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    /**
     * 新增用户
     * @param user
     */
    public void saveUser(User user){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        user.setCreateTime(sdf.format(new Date()));
        userDAO.saveUser(user);
    }

    /**
     * 更新用户
     * @param user
     */
    public void updateUser(User user){
        userDAO.updateUser(user);
    }

    /**
     * 查询验证用户
     * @param userName
     * @param pwd
     * @return
     */
    public boolean valUser(String userName,String pwd){
        if (userDAO.queryUser(userName,pwd) != null){
            return true;
        }
        return false;
    }

    /**
     * 分页查询用户
     * @param pageNo
     * @param pageSize
     * @param userName
     * @return list
     */
    public List<User> queryUserByPageList(Integer pageNo, Integer pageSize, String userName, String userType){
        return userDAO.queryUserByPageList(pageNo,pageSize,userName,userType);
    }

}
