package com.spring.demo.controllers;

import com.spring.demo.models.Payment;
import com.spring.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        var pays = payRepo.getPaymentsFromDeliveries(deliveries);
        return pays;
    }

    @GetMapping("/employeeHistory")
    public List<Payment> employeeTransactionHistory(@RequestParam String id){
        var emp = empRepo.findById(id).get();
        var shipments = emp.getShipment();
        var pays = payRepo.getPaymentsFromShipments(shipments);
        return pays;
    }

}
