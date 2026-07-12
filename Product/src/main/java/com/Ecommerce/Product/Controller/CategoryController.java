package com.Ecommerce.Product.Controller;

import com.Ecommerce.Product.DTO.CategoryRequest;
import com.Ecommerce.Product.Entity.Category;
import com.Ecommerce.Product.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/createCategory")
    public String createCategory(@RequestBody CategoryRequest categoryRequest)
    {
        System.out.println("Inside createCategory method");
        categoryService.addCategory(categoryRequest);
        return "Category Created Successfully";
    }

    @GetMapping("/allCategories")
    public List<Category> getAllCategories()
    {
        return categoryService.findAllCaegories();
    }

    @PutMapping("/updaeCategory/{category_id}")
    public Category updateCategory(@PathVariable long category_id, @RequestBody CategoryRequest categoryRequest)
    {
        return categoryService.updateCategory(category_id,categoryRequest);
    }

}
