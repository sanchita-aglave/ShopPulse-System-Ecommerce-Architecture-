package com.Ecommerce.Product.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private Long productId;

    private String productName;

    private String description;

    private Double price;

    private Double discountPercentage;

    private Double finalPrice;

    private Integer stockQuantity;

    private String brand;

    private String imageUrl;

    private Double averageRating;

    private Integer totalReviews;

    private Boolean active;

    private LocalDate createdAt;

    private LocalDate updateAt;

    private UUID sellerId;

    private Long categoryId;

    private String categoryName;
}
