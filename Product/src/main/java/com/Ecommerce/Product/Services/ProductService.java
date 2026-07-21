package com.Ecommerce.Product.Services;

import com.Ecommerce.Product.DTO.ProductRequest;
import com.Ecommerce.Product.DTO.ProductResponse;
import com.Ecommerce.Product.Entity.Category;
import com.Ecommerce.Product.Entity.Product;
import com.Ecommerce.Product.ExceptionHandler.CategoryNotFoundException;
import com.Ecommerce.Product.ExceptionHandler.ProductNotFoundException;
import com.Ecommerce.Product.Mapper.ProductMapper;
import com.Ecommerce.Product.Repository.CategoryRepository;
import com.Ecommerce.Product.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    public ProductResponse createProduct(ProductRequest productRequest, UUID sellerId)
    {
        Product product= ProductMapper.mapToProduct(productRequest);

        Category category=categoryRepository.findById(productRequest.getCategoryId()).orElseThrow(()->new CategoryNotFoundException("Category Not Found"));
        product.setSellerId(sellerId);
        product.setCategory(category);

        Double discount=productRequest.getDiscountPercentage()==null?0.0: productRequest.getDiscountPercentage();
        Double finalPrice=productRequest.getPrice()-((productRequest.getPrice()*discount/100));
        product.setFinalPrice(finalPrice);
        // Initial values
        product.setAverageRating(0.0);
        product.setTotalReviews(0);
        Product updatedProduct=productRepository.save(product);

       return ProductMapper.mapToProductResponse(updatedProduct);

    }

    public List<Product> showActiveProducts()
    {
        return productRepository.getAllActiveProducts();
    }

    public ProductResponse getProductById(Long productId)
    {
        Product product=productRepository.findById(productId).orElseThrow(()->new ProductNotFoundException("Product Not Found"));

        return ProductMapper.mapToProductResponse(product);
    }


}
