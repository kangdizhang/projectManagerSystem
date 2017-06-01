package com.ectrip.service.impl;

import com.ectrip.dao.UserDAO;
import com.ectrip.model.User;
import com.ectrip.service.UserService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

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
     * 查询用户
     * @param userName
     * @param pwd
     * @return
     */
    public User findUser(String userName,String pwd) {
        return userDAO.queryUser(userName,pwd);
    }

    /**
     * 主键查询用户
     * @param id
     * @return
     */
    public User findUserById(Integer id){
        return userDAO.findUserById(id);
    }

    /**
     * 分页查询用户
     * @param pageNo
     * @param pageSize
     * @param userName
     * @return list
     */
    public PageInfo<User> queryUserByPageList(Integer pageNo, Integer pageSize, String userName, String userType){
        List<User> list = userDAO.queryUserByPageList(pageNo,pageSize,userName,userType);
        logger.info("查询数据:{}", list.toString());
        return new PageInfo<User>(list);
    }

}
