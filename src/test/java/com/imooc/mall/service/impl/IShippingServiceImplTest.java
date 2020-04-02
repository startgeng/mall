package com.imooc.mall.service.impl;


import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.imooc.mall.form.ShippingFrom;
import com.imooc.mall.service.IShippingService;
import com.imooc.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class IShippingServiceImplTest {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Autowired
    private IShippingService shippingService;

    private Integer uid = 1;

    private Integer shpping = 7;
    @Test
    public void add() {
        ShippingFrom shippingFrom = new ShippingFrom();
        shippingFrom.setReceiverName("陈立庚");
        shippingFrom.setReceiverAddress("北京朝阳");
        shippingFrom.setReceiverCity("北京");
        shippingFrom.setReceiverDistrict("北京");
        shippingFrom.setReceiverMobile("11011011110");
        shippingFrom.setReceiverProvince("北京");
        shippingFrom.setReceiverZip("1110");
        shippingFrom.setReceiverPhone("1756564545");
        ResponseVo<Map<String, Integer>> responseVo = shippingService.add(uid, shippingFrom);
        log.info("responseVo->{}",responseVo);
    }

    @Test
    public void delete() {
        ResponseVo delete = shippingService.delete(uid, shpping);
        log.info("delete->{}",delete);
    }

    @Test
    public void update() {
        ShippingFrom shippingFrom = new ShippingFrom();
        shippingFrom.setReceiverCity("纽约");
        ResponseVo responseVo = shippingService.update(uid, shpping, shippingFrom);
        log.info("responseVo-{}",responseVo);
    }

    @Test
    public void list() {
        ResponseVo<PageInfo> responseVo = shippingService.list(uid, 10, 1);
        log.info("responseVo->{}",gson.toJson(responseVo));
    }
}