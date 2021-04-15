package com.spring.demo.controllers;

import com.spring.demo.models.Payment;
import com.spring.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final EmployeeRepository empRepo;
    private final PaymentRepository payRepo;
    private final ShoppingCartRepository cartRepo;

    @Autowired
    TransactionController(PaymentRepository payRepo, ShoppingCartRepository cartRepo, EmployeeRepository empRepo){
        this.empRepo = empRepo;
        this.payRepo = payRepo;
        this.cartRepo = cartRepo;
    }

    @GetMapping("/userHistory")
    public List<Payment> userTransactionHistory(@RequestParam String id){
        var cart = cartRepo.findById(id).get();
        var deliveries = cart.getDeliveries();
        var payments = deliveries.stream().map(payRepo::getPaymentByDelivery).collect(Collectors.toList());
        return payments;
    }

    @GetMapping("/employeeHistory")
    public List<Payment> employeeTransactionHistory(@RequestParam String id){
        var emp = empRepo.findById(id).get();
        var shipments = emp.getShipment();
        var payments = shipments.stream().map(payRepo::getPaymentByShipment).collect(Collectors.toList());
        return payments;
    }

}
