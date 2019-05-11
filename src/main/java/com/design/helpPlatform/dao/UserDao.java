package com.design.helpPlatform.dao;

import com.design.helpPlatform.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Mapper
public interface UserDao {

    /**
     * 新增用户
     * @param user
     * @return
     */
    int insertUserByUserInfo(User user);

    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     */
    User getUserByUerName(String userName);

    /**
     * 登陆
     * @param map
     * @return
     */
    User getUserByUseNameAndPassword(Map map);

    /**
     * 根据id查用户
     * @return
     */
    User getUserById(Integer userId);
}
