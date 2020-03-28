package com.imooc.mall.controller;


import com.imooc.mall.service.ICategoryService;
import com.imooc.mall.vo.CategoryVo;
import com.imooc.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @RequestMapping("/categories")
    public ResponseVo<List<CategoryVo>> selectAll(){
        return categoryService.selectAll();
    }
}
