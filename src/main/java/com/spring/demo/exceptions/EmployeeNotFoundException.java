package com.spring.demo.exceptions;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(String message){
        super("Employee with id '" + message + "' not found.");
    }
}
