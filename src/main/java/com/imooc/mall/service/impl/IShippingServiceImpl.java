package com.imooc.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.mall.dao.ShippingMapper;
import com.imooc.mall.enums.ResponseEnum;
import com.imooc.mall.form.ShippingFrom;
import com.imooc.mall.pojo.Shipping;
import com.imooc.mall.service.IShippingService;
import com.imooc.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class IShippingServiceImpl implements IShippingService {

    @Autowired
    private ShippingMapper shippingMapper;

    @Override
    public ResponseVo<Map<String, Integer>> add(Integer uid, ShippingFrom shipingFrom) {
        //将Shipping转化为ship
        Shipping shipping = new Shipping();
        BeanUtils.copyProperties(shipingFrom,shipping);
        shipping.setUserId(uid);
        //将ship插入数据库而获取到ship_id
        int i = shippingMapper.insertSelective(shipping);
        //判断ship插入的返回值是否==0
        if (i==0){
            return ResponseVo.error(ResponseEnum.ERROR);
        }
        //将shiip的id保存在map里面
        Map<String,Integer> map = new HashMap<>();
        map.put("shippingId",shipping.getId());
        return ResponseVo.success(map);
    }

    @Override
    public ResponseVo delete(Integer uid, Integer shippingId) {
        //要根据uid和shpping一起来删除
        Integer resultCount = shippingMapper.deleteByUerIdAndShppingId(shippingId, uid);
        if (resultCount==0){
            return ResponseVo.error(ResponseEnum.DELETE_SHPPING_FAILED);
        }
        return ResponseVo.success();
    }

    @Override
    public ResponseVo update(Integer uid, Integer shippingId, ShippingFrom shippingFrom) {
        Shipping shipping = new Shipping();
        BeanUtils.copyProperties(shippingFrom,shipping);
        shipping.setUserId(uid);
        shipping.setId(shippingId);
        Integer resultCount = shippingMapper.updateByPrimaryKeySelective(shipping);
        if (resultCount == 0){
            return ResponseVo.error(ResponseEnum.SHIPPING_UPDATE_FAILED);
        }
        return ResponseVo.success();
    }

    @Override
    public ResponseVo<PageInfo> list(Integer uid, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        List<Shipping> shippingList = shippingMapper.selectByUid(uid);
        PageInfo pageInfo = new PageInfo(shippingList);
        return ResponseVo.success(pageInfo);
    }
}
