package com.imooc.mall.service;

import com.github.pagehelper.PageInfo;
import com.imooc.mall.form.ShippingFrom;
import com.imooc.mall.vo.ResponseVo;

import java.util.Map;

public interface IShippingService {

    /**
     * 添加收获地址
     * @param uid
     * @param shipingFrom
     * @return
     */
    ResponseVo<Map<String,Integer>> add(Integer uid, ShippingFrom shipingFrom);

    /**
     * 删除收获地址
     * @param shippingId
     * @return
     */
    ResponseVo delete(Integer uid,Integer shippingId);

    /**
     *  更新收获地址
     * @param shippingFrom
     * @return
     */
    ResponseVo update(Integer uid,Integer shippingId,ShippingFrom shippingFrom);

    /**
     * 获取到地址列表
     * @param uid
     * @param pageSize
     * @param pageNum
     * @return
     */
    ResponseVo<PageInfo> list(Integer uid,Integer pageSize,Integer pageNum);
}
