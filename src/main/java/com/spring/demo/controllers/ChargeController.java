package com.spring.demo.controllers;

import com.spring.demo.models.ChargeRequest;
import com.spring.demo.repositories.DeliveryRepository;
import com.spring.demo.repositories.PaymentRepository;
import com.spring.demo.repositories.ShoppingCartRepository;
import com.spring.demo.repositories.StripeRepository;
import com.spring.demo.services.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/charge")
public class ChargeController {

    private final ShoppingCartRepository cartRepo;
    private final DeliveryRepository delRepo;
    private final PaymentRepository payRepo;
    private final StripeRepository stripeRepo;

    @Autowired
    public ChargeController(ShoppingCartRepository cartRepo, DeliveryRepository delRepo,
                            PaymentRepository payRepo, StripeRepository stripeRepo){
        this.cartRepo = cartRepo;
        this.delRepo = delRepo;
        this.payRepo = payRepo;
        this.stripeRepo = stripeRepo;
    }

    @PostMapping("/create")
    public ChargeRequest createCharge(@RequestBody ChargeRequest chargeRequest){
        stripeRepo.save(chargeRequest);
        return chargeRequest;
    }

    @PostMapping("/post")
    public String charge(ChargeRequest chargeRequest, Model model)
            throws StripeException {
        chargeRequest.setDescription("Example charge");
        chargeRequest.setCurrency(ChargeRequest.Currency.EUR);
        Charge charge = StripeService.charge(chargeRequest);
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());
        return "result";
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "no result";
    }
}