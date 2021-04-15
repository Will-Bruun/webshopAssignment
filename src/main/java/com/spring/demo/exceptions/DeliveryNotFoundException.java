package com.spring.demo.exceptions;

public class DeliveryNotFoundException extends RuntimeException{
    public DeliveryNotFoundException(String message){
        super("Delivery with id '" + message +"' not found");
    }
}
