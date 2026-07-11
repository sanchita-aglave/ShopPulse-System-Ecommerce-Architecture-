package com.Ecommerce.Product.DTO;

import com.Ecommerce.Product.Entity.Category;
import com.Ecommerce.Product.Entity.Inventory;
import com.Ecommerce.Product.Entity.Review;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ProductRequest {

    private String productName;

    private String description;

    private Double price;

    private Double discountPercentage;

    private Integer stockQuantity;

    private String brand;

    private String imageUrl;

    private Boolean active;

    private Long categoryId;


}
