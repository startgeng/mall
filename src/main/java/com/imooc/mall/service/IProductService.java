package com.imooc.mall.service;

import com.github.pagehelper.PageInfo;
import com.imooc.mall.vo.ProductDetailVo;
import com.imooc.mall.vo.ProductVo;
import com.imooc.mall.vo.ResponseVo;

import java.util.List;


public interface IProductService {

    /**
     * 获取到一个商品Id
     * @param productId
     * @return
     */
    ResponseVo<ProductDetailVo> detail(Integer productId);

    /**
     * 获取商品列表
     * @param categoryId
     * @param pageNum
     * @param pageSize
     * @return
     */
    ResponseVo<PageInfo> list(Integer categoryId, Integer pageNum, Integer pageSize);
}
