package com.imooc.mall.dao;

import com.imooc.mall.pojo.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class CategoryMapperTest {

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void findById() {
        Category byId = categoryMapper.selectByPrimaryKey(100001);
        System.out.println(byId.toString());
    }

//    @Test
//    public void queryById() {
//        Category category = categoryMapper.queryById(100001);
//        System.out.println(category.toString());
//    }
}