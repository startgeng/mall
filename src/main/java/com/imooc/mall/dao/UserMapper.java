package com.imooc.mall.dao;

import com.imooc.mall.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 查询用户是否存在
     * @param username 用户名
     * @return
     */
    Integer countByUsername(String username);

    /**
     * 查询邮件是否存在
     * @param email 邮件
     * @return
     */
    Integer countByEmail(String email);

    /**
     * 查询用户
     * @param username
     * @return
     */
    User selectByUsername(String username);
}