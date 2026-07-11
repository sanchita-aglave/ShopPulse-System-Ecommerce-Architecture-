package com.Ecommerce.Product.Controller;

import com.Ecommerce.Product.DTO.ProductRequest;
import com.Ecommerce.Product.Entity.Product;
import com.Ecommerce.Product.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/Product")
public class ProductController {

    @Autowired
    private ProductService productService;

//    @PreAuthorize("hasRole('SELLER')")
    @PostMapping("/addProduct")
    public String addProduct(@RequestHeader("User-Id")UUID sellerId, @RequestBody ProductRequest productRequest)
    {
        System.out.println("Inside Product Controller");

        return productService.createProduct(productRequest,sellerId);
    }

    @GetMapping("/getActiveProducts")
    public List<Product> GetActiveProducts()
    {
        return productService.showActiveProducts();
    }

}
