package com.imooc.mall.respority;

import com.imooc.mall.pojo.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ReporityCategoryTest {

    @Autowired
    private ReporityCategory reporityCategory;

    @Test
    public void findById() {
        Category byId = reporityCategory.findById(100001);
        System.out.println(byId.toString());
    }
}