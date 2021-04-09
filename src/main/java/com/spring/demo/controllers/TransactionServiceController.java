package com.spring.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionServiceController {

    //With this one it is simple, we want to have two methods in here. One for allowing the user to see their transaction history.
    //The other for allowing employees to check on how much they spend on shipments
    //Two way transactions logs.
    //The user one can start from their id, to cart, to deliveries connected to that cart, and then finally to payments with info, datetime in a list
    //The company one will be harder I think

}
