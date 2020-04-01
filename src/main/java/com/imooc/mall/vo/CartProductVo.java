package com.imooc.mall.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProductVo {

    private Integer productId;

    private Integer quantity;

    private String productName;

    private String productSubtitle;

    private String productMainImage;

    /***
     *   单价 * 数量
     * ***/
    private BigDecimal productPrice;

    private Integer productStatus;

    private BigDecimal productTotalPrice;

    private Integer productStock;

    private Boolean productSelected;
}
