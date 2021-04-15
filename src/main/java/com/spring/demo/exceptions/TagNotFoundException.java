package com.spring.demo.exceptions;

public class TagNotFoundException extends RuntimeException{
    public TagNotFoundException(String message){
        super("Tag with '" + message + "' not found.");
    }
}
