package com.imooc.mall.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryVo {

    private Integer id;

    private Integer parentId;

    private String name;

    private Integer sortOrder;

    private List<CategoryVo> subCategories;
}
