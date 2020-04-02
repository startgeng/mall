package com.imooc.mall.controller;

import com.github.pagehelper.PageInfo;
import com.imooc.mall.consts.MallConst;
import com.imooc.mall.form.ShippingFrom;
import com.imooc.mall.pojo.User;
import com.imooc.mall.service.IShippingService;
import com.imooc.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

@RestController
public class ShippingController {

    @Autowired
    private IShippingService shippingService;

    @PostMapping("/ship/add")
    public ResponseVo<Map<String, Integer>> add(@Valid @RequestBody ShippingFrom shipingFrom,
                                                HttpSession session) {
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return shippingService.add(user.getId(), shipingFrom);
    }

    @DeleteMapping("/ship/delete")
    public ResponseVo delete(HttpSession session, Integer shippingId) {
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return shippingService.delete(user.getId(), shippingId);
    }

    @PutMapping("/ship/update")
    public ResponseVo update(@RequestBody @Valid HttpSession session, Integer shippingId
            , ShippingFrom shippingFrom) {
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return shippingService.update(user.getId(), shippingId, shippingFrom);
    }

    @GetMapping("/ship/list")
    public ResponseVo<PageInfo> list(HttpSession session,
                                     @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                     @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return shippingService.list(user.getId(),pageSize,pageNum);
    }
}
