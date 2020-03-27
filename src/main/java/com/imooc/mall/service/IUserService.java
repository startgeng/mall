package com.imooc.mall.service;

import com.imooc.mall.pojo.User;
import com.imooc.mall.vo.ResponseVo;

public interface IUserService {

    /**
     * 注册用户
     * @param user
     */
    ResponseVo<User> register(User user);

    /**
     * 登陆用户
     * @param username
     * @param password
     * @return
     */
    ResponseVo<User> login(String username,String password);
}
