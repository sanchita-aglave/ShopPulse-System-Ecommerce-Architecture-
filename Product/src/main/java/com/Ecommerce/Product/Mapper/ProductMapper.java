package com.Ecommerce.Product.Mapper;

import com.Ecommerce.Product.DTO.ProductRequest;
import com.Ecommerce.Product.Entity.Product;

public class ProductMapper {
   public static Product mapToProduct(ProductRequest productRequest) {
      return Product.builder()
              .productName(productRequest.getProductName())
              .description((productRequest.getDescription()))
              .price(productRequest.getPrice())
              .discountPercentage(productRequest.getDiscountPercentage())
              .stockQuantity(productRequest.getStockQuantity())
              .brand(productRequest.getBrand())
              .imageUrl(productRequest.getImageUrl())
              .active(productRequest.getActive())
              .build();

   }
}
