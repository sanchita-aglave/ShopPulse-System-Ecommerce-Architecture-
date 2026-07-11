package com.Ecommerce.Product.Services;

import com.Ecommerce.Product.DTO.CategoryRequest;
import com.Ecommerce.Product.Entity.Category;
import com.Ecommerce.Product.ExceptionHandler.CategoryNotFoundException;
import com.Ecommerce.Product.Mapper.CategoryMapper;
import com.Ecommerce.Product.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public String addCategory(CategoryRequest categoryRequest)
    {
        Category category= CategoryMapper.mapToCategory(categoryRequest);
        categoryRepository.save(category);
        return "Category Created Successfully";
    }

    public List<Category> findAllCaegories()
    {
        return categoryRepository.findAll();
    }

    public Category updateCategory(long category_id, CategoryRequest categoryRequest)
    {
        Category category=categoryRepository.findById(category_id).orElseThrow(()->new CategoryNotFoundException("Category Not Found"));

        Category caegoryBuild=Category.builder()
                .categoryId(category_id)
                .categoryName(categoryRequest.getCategoryName())
                .categoryDescription(categoryRequest.getCategoryDescription())
                .build();

        return categoryRepository.save(caegoryBuild);
    }
}
