package com.Ecommerce.Order.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

    private Long productId;

    private String productName;

    private String description;

    private BigDecimal price;

    private BigDecimal discountPercentage;

    private BigDecimal finalPrice;

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
