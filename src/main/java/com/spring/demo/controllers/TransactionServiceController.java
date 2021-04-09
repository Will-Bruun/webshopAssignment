package com.spring.demo.controllers;

import com.spring.demo.models.Payments;
import com.spring.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionServiceController {

    //With this one it is simple, we want to have two methods in here. One for allowing the user to see their transaction history.
    //The other for allowing employees to check on how much they spend on shipments
    //Two way transactions logs.
    //The user one can start from their id, to cart, to deliveries connected to that cart, and then finally to payments with info, datetime in a list
    //The company one will be harder I think

    private final EmployeeRepository empRepo;
    private final PaymentRepository payRepo;
    private final ShoppingCartRepository cartRepo;

    @Autowired
    TransactionServiceController(PaymentRepository payRepo, ShoppingCartRepository cartRepo, EmployeeRepository empRepo){
        this.empRepo = empRepo;
        this.payRepo = payRepo;
        this.cartRepo = cartRepo;
    }

    @GetMapping("/userHistory")
    public List<Payments> userTransactionHistory(@RequestParam String id){
        var cart = cartRepo.findById(id).get();
        var deliveries = cart.getDeliveries();
        var pays = payRepo.getPaymentsFromDeliveries(deliveries);
        return pays;
    }

    @GetMapping("/employeeHistory")
    public List<Payments> employeeTransactionHistory(@RequestParam String id){
        var emp = empRepo.findById(id).get();
        var shipments = emp.getShipment();
        var pays = payRepo.getPaymentsFromShipments(shipments);
        return pays;
    }

}
