package com.imooc.mall.controller;

import com.imooc.mall.consts.MallConst;
import com.imooc.mall.enums.ResponseEnum;
import com.imooc.mall.form.UserLoginForm;
import com.imooc.mall.form.UserRegisterForm;
import com.imooc.mall.pojo.User;
import com.imooc.mall.service.IUserService;
import com.imooc.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@RestController
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/user/register")
    public ResponseVo register(@Valid @RequestBody UserRegisterForm userForm,
                               BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            log.error("注册的参数错误,{},{}",bindingResult);
            return ResponseVo.error(ResponseEnum.ERROR);
        }
        User user = new User();
        BeanUtils.copyProperties(userForm,user);
        return userService.register(user);
    }

    @PostMapping("/user/login")
    public ResponseVo login(@Valid @RequestBody UserLoginForm userLoginForm,
                            BindingResult bindingResult, HttpSession session){
        if (bindingResult.hasErrors()){
            return ResponseVo.error(ResponseEnum.ERROR);
        }
        ResponseVo responseVo = userService.login(userLoginForm.getUsername(), userLoginForm.getPassword());
        session.setAttribute(MallConst.CURRENT_USER,responseVo.getData());
        return responseVo;
    }

    @GetMapping("/userInfo")
    public ResponseVo userInfo(HttpSession session){
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return ResponseVo.success(user);
    }

    @PostMapping("/user/logout")
    public ResponseVo logout(HttpSession session){
        session.removeAttribute(MallConst.CURRENT_USER);
        return ResponseVo.success();
    }
}
