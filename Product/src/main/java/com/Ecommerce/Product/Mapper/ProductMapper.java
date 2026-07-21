package com.Ecommerce.Product.Mapper;

import com.Ecommerce.Product.DTO.ProductRequest;
import com.Ecommerce.Product.DTO.ProductResponse;
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

   public static ProductResponse mapToProductResponse(Product product)
   {
      return ProductResponse.builder()
              .productId(product.getProductId())
              .productName(product.getProductName())
              .description(product.getDescription())
              .price(product.getPrice())
              .discountPercentage(product.getDiscountPercentage())
              .finalPrice(product.getFinalPrice())
              .stockQuantity(product.getStockQuantity())
              .brand(product.getBrand())
              .imageUrl(product.getImageUrl())
              .averageRating(product.getAverageRating())
              .totalReviews(product.getTotalReviews())
              .active(product.getActive())
              .createdAt(product.getCreatedAt())
              .updateAt(product.getUpdateAt())
              .sellerId(product.getSellerId())
              .categoryId(
                      product.getCategory() != null
                              ? product.getCategory().getCategoryId()
                              : null
              )
              .categoryName(
                      product.getCategory() != null
                              ? product.getCategory().getCategoryName()
                              : null
              )
              .build();
   }
}
