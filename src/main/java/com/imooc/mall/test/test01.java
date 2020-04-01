package com.imooc.mall.test;

import java.math.BigDecimal;

public class test01 {
    public static void main(String[] args) {
        BigDecimal b1 = new BigDecimal(String.valueOf(0.01));
        System.out.println(b1.add(BigDecimal.valueOf(0.09)));
    }
}
