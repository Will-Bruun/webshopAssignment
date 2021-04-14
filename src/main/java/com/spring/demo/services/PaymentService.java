package com.spring.demo.services;

import com.spring.demo.models.Delivery;
import com.spring.demo.models.Payment;

import java.time.LocalDateTime;

public class PaymentService {

    public static Payment createPayment(Delivery deliv,String info){
        Payment pay = new Payment();
        pay.setDelivery(deliv);
        pay.setInfo(info);
        pay.setDate(LocalDateTime.now());
        return pay;
    }
}
