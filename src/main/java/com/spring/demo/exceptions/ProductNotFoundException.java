package com.spring.demo.exceptions;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(String message){
        super("Product with '" + message + "' not found.");
    }

}
