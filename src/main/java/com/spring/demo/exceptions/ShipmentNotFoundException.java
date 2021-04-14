package com.spring.demo.exceptions;

public class ShipmentNotFoundException extends RuntimeException {
    public ShipmentNotFoundException(String message){
        super("Shipment with id '" + message + "' not found.");
    }
}
