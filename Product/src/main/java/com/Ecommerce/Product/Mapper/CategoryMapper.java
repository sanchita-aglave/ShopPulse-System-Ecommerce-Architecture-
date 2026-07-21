package com.Ecommerce.Product.Mapper;

import com.Ecommerce.Product.DTO.CategoryRequest;
import com.Ecommerce.Product.Entity.Category;

public class CategoryMapper {

    public static Category mapToCategory(CategoryRequest categoryRequest)
    {
        return Category.builder()
                .categoryName(categoryRequest.getCategoryName())
                .categoryDescription(categoryRequest.getCategoryDescription())
                .build();
    }
}




