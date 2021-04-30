package com.spring.demo.controllers;

import com.spring.demo.models.ChargeRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    @Value("stripe.keys.public")
    private String stripePublicKey;

    @RequestMapping("/get")
    public String checkout(Model model) {
        model.addAttribute("amount", 50 * 100);
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.USD);
        return "checkout";
    }
}