package com.spring.demo.controllers;

import com.spring.demo.exceptions.CartNotFoundException;
import com.spring.demo.models.Delivery;
import com.spring.demo.models.Payment;
import com.spring.demo.models.Product;
import com.spring.demo.models.ShoppingCart;
import com.spring.demo.services.CartService;
import com.spring.demo.services.PaymentService;
import com.spring.demo.services.ShoppingService;
import com.spring.demo.repositories.DeliveryRepository;
import com.spring.demo.repositories.PaymentRepository;
import com.spring.demo.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/shopping")
public class ShoppingController {

    private final ShoppingCartRepository cartRepo;
    private final DeliveryRepository delRepo;
    private final PaymentRepository payRepo;

    @Autowired
    public ShoppingController(ShoppingCartRepository cartRepo, DeliveryRepository delRepo, PaymentRepository payRepo){
        this.cartRepo = cartRepo;
        this.delRepo = delRepo;
        this.payRepo = payRepo;

    }

    @PutMapping("/addItem")
    public ShoppingCart addProductToCart(@RequestBody Product prod, @RequestParam String id){
        try{
            var userOpt = cartRepo.findById(id);
            if(userOpt.isPresent()){
                var cart = userOpt.get();
                cart = CartService.appendProduct(prod, cart);

                cartRepo.save(cart);
                return cart;
            } else {
                throw new CartNotFoundException(id);
            }

        } catch(NoSuchElementException e){
            e.printStackTrace();
            throw new CartNotFoundException(id);
        }

    }

    @PutMapping("/removeItem")
    public ShoppingCart removeProductFromCart(@RequestBody Product prod, @RequestParam String id){
        try{
            var userOpt = cartRepo.findById(id);
            if(userOpt.isPresent()){
                var cart = userOpt.get();
                cart = CartService.removeProduct(prod, cart);

                cartRepo.save(cart);
                return cart;
            } else {
                throw new CartNotFoundException(id);
            }
        } catch(NoSuchElementException e){
            e.printStackTrace();
            throw new CartNotFoundException(id);
        }
    }

    public double summarizeCost(@RequestParam String id){
        double sum = 0;
        try {
            var userOpt = cartRepo.findById(id);
            if(userOpt.isPresent()){
                var cart = userOpt.get();
                List<Product> prods = cart.getProducts();

                for(Product prod : prods){
                    sum += prod.getPrice();
                }
            }
            return sum;
        } catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public List<Product> removeAllProductsFromCart(@RequestParam String id){
        var userOpt = cartRepo.findById(id);
        if(userOpt.isPresent()){
            var cart = userOpt.get();
            List<Product> list = cart.getProducts();
            cart = CartService.removeAllProducts(cart);

            cartRepo.save(cart);
            return list;
        } else {
            throw new CartNotFoundException(id);
        }

    }

    public Delivery createDelivery(@RequestParam String address, @RequestParam String id){
        var userOpt = cartRepo.findById(id).get();
        Delivery delivery = ShoppingService.createDelivery(address, userOpt);
        ShoppingCart cart = ShoppingService.connectDelivery(userOpt, delivery);
        cartRepo.save(cart);
        delRepo.save(delivery);
        return delivery;
    }

    public void createPayment(@RequestBody Delivery deliv, @RequestParam String info){
        Payment pay = PaymentService.createPayment(deliv, info);
        payRepo.save(pay);
    }

}
