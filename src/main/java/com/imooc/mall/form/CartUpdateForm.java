package com.imooc.mall.form;

import lombok.Data;

@Data
public class CartUpdateForm {

    private Integer quantity;

    private Boolean selected;

    public CartUpdateForm(Integer quantity) {
        this.quantity = quantity;
    }

    public CartUpdateForm(Boolean selected) {
        this.selected = selected;
    }

    public CartUpdateForm() {

    }
}
