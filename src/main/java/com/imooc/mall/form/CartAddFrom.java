package com.imooc.mall.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CartAddFrom {

    @NotNull
    private Integer productId;

    private Boolean selected = true;
}
