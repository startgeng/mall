package com.imooc.mall.service;

import com.imooc.mall.form.CartAddFrom;
import com.imooc.mall.form.CartUpdateForm;
import com.imooc.mall.vo.CartVo;
import com.imooc.mall.vo.ResponseVo;

public interface ICartService {

    /**
     * 添加到购物车
     * @param uid
     * @param cartAddFrom
     * @return
     */
    ResponseVo<CartVo> add(Integer uid, CartAddFrom cartAddFrom);

    /**
     * 获得购物车的列表
     * @param uid
     * @return
     */
    ResponseVo<CartVo> list(Integer uid);

    /**
     * 更新购物车
     * @param uid
     * @param productId
     * @param cartUpdateForm
     * @return
     */
    ResponseVo<CartVo> update(Integer uid, Integer productId, CartUpdateForm cartUpdateForm);

    /**
     * 删除购物车
     * @param uid
     * @param productId
     * @return
     */
    ResponseVo<CartVo> delete(Integer uid,Integer productId);

    /***
     * 全选
     * @param uid
     * @return
     */
    ResponseVo<CartVo> selectAll(Integer uid);

    /**
     * 全不选
     * @param uid
     * @return
     */
    ResponseVo<CartVo> unSelectAll(Integer uid);

    /**
     * 累加
     * @param uid
     * @return
     */
    ResponseVo<Integer> sum(Integer uid);
}
