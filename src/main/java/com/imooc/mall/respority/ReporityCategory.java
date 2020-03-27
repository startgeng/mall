package com.imooc.mall.respority;

import com.imooc.mall.pojo.Category;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;


@MapperScan
public interface ReporityCategory extends JpaRepository<Category,Integer> {


    Category findById(int id);


}
