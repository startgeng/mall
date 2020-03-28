package com.imooc.mall.service.impl;

import com.imooc.mall.consts.MallConst;
import com.imooc.mall.dao.CategoryMapper;
import com.imooc.mall.pojo.Category;
import com.imooc.mall.service.ICategoryService;
import com.imooc.mall.vo.CategoryVo;
import com.imooc.mall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ResponseVo<List<CategoryVo>> selectAll() {
        List<Category> categories = categoryMapper.selectAll();
//        List<CategoryVo> categoryVoList = new ArrayList<>();
//        for (Category category : categories) {
//            if (category.getParentId().equals(MallConst.ROOT_PARENT_ID)){
//                CategoryVo categoryVo = new CategoryVo();
//                BeanUtils.copyProperties(category,categoryVo);
//                categoryVoList.add(categoryVo);
//            }
//        }
        //查出所有的父节点
        List<CategoryVo> categoryVoList = categories.stream().filter(e -> e.getParentId().equals(MallConst.ROOT_PARENT_ID))
                .map(e -> category2CategoryVo(e))
                .sorted(Comparator.comparing(CategoryVo::getSortOrder).reversed())
                .collect(Collectors.toList());

        //查询子节点
        findSubCategory(categoryVoList,categories);
        return ResponseVo.success(categoryVoList);
    }
    //转换为CategoryVo
    private CategoryVo category2CategoryVo(Category category){
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category,categoryVo);
        return categoryVo;
    }

    //遍历根节点
    private void findSubCategory(List<CategoryVo> categoryVoList,List<Category> categories){
        for (CategoryVo categoryVo:categoryVoList){
            List<CategoryVo> subCategoryVoList = new ArrayList<>();
            for (Category category:categories){
                if (categoryVo.getId().equals(category.getParentId())){
                    CategoryVo subcategoryVo = category2CategoryVo(category);
                    subCategoryVoList.add(subcategoryVo);
                }
//                subCategoryVoList.sort(Comparator.comparing(CategoryVo::getSortOrder).reversed());
                subCategoryVoList.sort(Comparator.comparing(CategoryVo::getSortOrder).reversed());
                categoryVo.setSubCategories(subCategoryVoList);
                findSubCategory(subCategoryVoList,categories);
            }
        }
    }
}
