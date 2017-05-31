package com.ectrip.dao;

import com.ectrip.model.User;
import com.ectrip.model.Version;
import com.ectrip.vo.VersionVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/5/11 0011.
 */
public interface UserDAO {

    /**
     * 新增版本
     * @param user
     */
    void saveUser(User user);

    void updateUser(User user);

    void updateLastLoginTime(User user);

    User queryUser(@Param("name")String name, @Param("pwd")String pwd);

    /**
     * @param pageNo
     * @param pageSize
     * @param name
     * @return list
     */
    List<VersionVO> queryUserByPageList(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize,
                                 @Param("name") String name);
}
