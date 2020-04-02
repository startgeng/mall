package com.imooc.mall.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseEnum {

    ERROR(-1, "服务端异常"),

    PASSWORD_ERROR(1, "密码错误"),

    //    SUCCESS(0,"成功"),
    SUCCESS(0, "成功"),

    USER_EXIST(2, "用户已存在"),

    PARAM_ERROR(3, "参数错误"),

    EMAIL_ERROR(4, "邮箱已存在"),

    NEED_LOGIN(10, "用户未登录,无法获取当前用户信息"),

    USER_OR_PASSWORD_ERROR(11, "用户名和密码不正确"),

    PRODUCT_OFF_SALE_OR_DELETE(12,"商品下架或删除"),

    PRODUCT_NOT_EXIT(13,"商品不存在"),

    PRODUCT_STOCK_ERROR(13,"商品库存不足"),

    CART_PRODUCT_NOT_EXIST(14,"购物车商品不存在"),

    DELETE_SHPPING_FAILED(15,"删除购物车失败"),

    SHIPPING_UPDATE_FAILED(16,"购物车更新失败"),
    ;
    private Integer code;

    private String desc;

}
