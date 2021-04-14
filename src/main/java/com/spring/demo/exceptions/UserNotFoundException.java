package com.spring.demo.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super("User with id' " + message + "' not found.");
    }
}
