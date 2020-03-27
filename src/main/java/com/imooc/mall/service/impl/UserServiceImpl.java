package com.imooc.mall.service.impl;

import com.imooc.mall.dao.UserMapper;
import com.imooc.mall.enums.ResponseEnum;
import com.imooc.mall.enums.RoleEnum;
import com.imooc.mall.pojo.User;
import com.imooc.mall.service.IUserService;
import com.imooc.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 注册用户
     * @param user 用户对象
     * @return
     */
    @Override
    public ResponseVo<User> register(User user) {
        //先判断用户名是否存在
        Integer resultCount = userMapper.countByUsername(user.getUsername());
        if (resultCount > 0){
//            throw new RuntimeException("改用户已注册");
//            error();
            return ResponseVo.error(ResponseEnum.USER_EXIST);
        }
        resultCount = userMapper.countByEmail(user.getEmail());
        if (resultCount > 0){
//            throw new RuntimeException("改邮箱已注册");
            return ResponseVo.error(ResponseEnum.EMAIL_ERROR);
        }

//        user.setRole(RoleEnum.CUSTOMER.getCode());
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8)));
        resultCount = userMapper.insertSelective(user);
        if (resultCount == 0){
//            throw new RuntimeException("注册失败");
            return ResponseVo.error(ResponseEnum.ERROR);
        }
        return ResponseVo.success();
    }

    /**
     * 登陆用户
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @Override
    public ResponseVo<User> login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user==null){
            //用户名和密码不正确
            return ResponseVo.error(ResponseEnum.USER_OR_PASSWORD_ERROR);
        }
        if (!user.getPassword().equalsIgnoreCase(DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)))){
             //用户名和密码不正确
            return ResponseVo.error(ResponseEnum.USER_OR_PASSWORD_ERROR);
        };
        user.setPassword("");
        return ResponseVo.success(user);
    }

    /**
     * 测试异常
     */
    private static void error(){
        throw new RuntimeException("服务器异常");
    }
}
