package com.ectrip.dao;

import com.ectrip.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/5/11 0011.
 */
public interface UserDAO {

    /**
     * 新增用户
     * @param user
     */
    void saveUser(User user);

    /**
     * 更新用户
     * @param user
     */
    void updateUser(User user);

    /**
     * 查询验证用户
     * @param name
     * @param pwd
     * @return
     */
    User queryUser(@Param("userName")String userName, @Param("pwd")String pwd);

    /**
     * 分页查询用户
     * @param pageNo
     * @param pageSize
     * @param userName
     * @return list
     */
    List<User> queryUserByPageList(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize,
                                 @Param("userName") String userName,@Param("userType") String userType);
}
