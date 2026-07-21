package com.Ecommerce.Order.Client;

import com.Ecommerce.Order.DTO.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    public ProductResponse getProduct(Long productId)
    {
        String url="http://localhost:8081/Product/getProduct/" + productId;
        return restTemplate.getForObject(url,ProductResponse.class);
    }
}
