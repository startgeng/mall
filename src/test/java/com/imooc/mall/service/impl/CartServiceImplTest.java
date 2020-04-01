package com.imooc.mall.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.imooc.mall.form.CartAddFrom;
import com.imooc.mall.form.CartUpdateForm;
import com.imooc.mall.service.ICartService;
import com.imooc.mall.vo.CartVo;
import com.imooc.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class CartServiceImplTest {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Autowired
    private ICartService cartService;

    @Test
    public void add() {
        Integer id = 1;
        CartAddFrom cartAddFrom = new CartAddFrom();
        cartAddFrom.setProductId(26);
        cartAddFrom.setSelected(false);
        cartService.add(1,cartAddFrom);
    }

    @Test
    public void list(){
        ResponseVo<CartVo> list = cartService.list(1);
        log.info("list={}",gson.toJson(list));
    }

    @Test
    public void update(){
        CartUpdateForm cartUpdateForm = new CartUpdateForm();
        cartUpdateForm.setQuantity(10);
        cartUpdateForm.setSelected(true);
        ResponseVo<CartVo> cartVoResponseVo = cartService.update(1, 26, cartUpdateForm);
        log.info("cartVoResponseVo={}",gson.toJson(cartVoResponseVo));
    }

    @Test
    public void delete(){
        ResponseVo<CartVo> delete = cartService.delete(1, 28);
        log.info("delete->{}",gson.toJson(delete));
    }

    @Test
    public void selectAll(){
        ResponseVo<CartVo> cartVoResponseVo = cartService.selectAll(1);
        log.info("cartVoResponseVo->{}",gson.toJson(cartVoResponseVo));
    }
    @Test
    public void unselectAll(){
        ResponseVo<CartVo> cartVoResponseVo = cartService.unSelectAll(1);
        log.info("cartVoResponseVo->{}",gson.toJson(cartVoResponseVo));
    }

    @Test
    public void sum(){
        ResponseVo<Integer> cartVoResponseVo = cartService.sum(1);
        log.info("cartVoResponseVo->{}",gson.toJson(cartVoResponseVo));
    }
}