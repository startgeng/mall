package com.imooc.mall.service.impl;

import com.imooc.mall.enums.RoleEnum;
import com.imooc.mall.pojo.User;
import com.imooc.mall.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
//@Transactional
public class UserServiceImplTest {

    @Autowired
    private IUserService userService;

    @Test
    public void register() {
        User user = new User("jack","123456","jack@qq.com", RoleEnum.CUSTOMER.getCode());
        userService.register(user);
    }
}