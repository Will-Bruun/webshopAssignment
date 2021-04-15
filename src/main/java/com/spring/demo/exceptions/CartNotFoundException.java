package com.spring.demo.exceptions;

public class CartNotFoundException extends RuntimeException{
    public CartNotFoundException(String message){
        super("Cart with id '" + message+ "' not found.");
    }
}
