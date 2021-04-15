package com.spring.demo.exceptions;

public class ManufacturerNotFoundException extends RuntimeException{

    public ManufacturerNotFoundException(String message){
        super("Manufacturer with id '" + message + "' not found.");
    }

}
