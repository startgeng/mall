package com.imooc.mall.dao;

import com.imooc.mall.pojo.Shipping;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShippingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Shipping record);

    int insertSelective(Shipping record);

    Shipping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Shipping record);

    int updateByPrimaryKey(Shipping record);

    /**
     * 删除收获地址
     * @param id
     * @param userId
     * @return
     */
    Integer deleteByUerIdAndShppingId(@Param("id")Integer id,@Param("userId")Integer userId);

    List<Shipping> selectByUid(@Param("userId")Integer userId);
}