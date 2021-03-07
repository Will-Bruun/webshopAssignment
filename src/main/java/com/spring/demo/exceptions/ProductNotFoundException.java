package com.spring.demo.exceptions;

public class ProductNotFoundException {
    private final String message;


    public ProductNotFoundException(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
