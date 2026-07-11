package com.Ecommerce.Product.ExceptionHandler;

public class CategoryNotFoundException extends RuntimeException{

    public CategoryNotFoundException(String message)
    {
        super(message);
    }
}
