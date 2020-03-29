package com.imooc.mall.service;


import com.imooc.mall.vo.CategoryVo;
import com.imooc.mall.vo.ResponseVo;

import java.util.List;
import java.util.Set;

public interface ICategoryService {

    /**
     * 查询到子分类的Id
     * @param id
     * @param resultSet
     */
    void findSubCategoryId(Integer id, Set<Integer> resultSet);

    /**
     * 查询所有的数据
     * @return
     */
    ResponseVo<List<CategoryVo>> selectAll();
}
