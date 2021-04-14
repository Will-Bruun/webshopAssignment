package com.spring.demo.controllers;

import com.spring.demo.exceptions.CartNotFoundException;
import com.spring.demo.models.ShoppingCart;
import com.spring.demo.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {

    private ShoppingCartRepository repo;

    @Autowired
    public CartController(ShoppingCartRepository repo){
        this.repo = repo;
    }

    @GetMapping("/get")
    public ShoppingCart getShoppingCart(@RequestParam String id){
        var userOpt = repo.findById(id);
        return userOpt.orElseThrow(() -> new CartNotFoundException(id));
    }

    @PostMapping("/post")
    public ShoppingCart createShoppingCart(@RequestBody ShoppingCart cart){
        repo.save(cart);
        return cart;
    }

    @PutMapping("/edit")
    public ShoppingCart editShoppingCart(@RequestBody ShoppingCart cart){
        Optional<ShoppingCart> cartInDb = repo.findById(cart.getId());
        if (cartInDb.isPresent()){
            repo.save(cart);
            return cart;
        } else {
            throw new CartNotFoundException(cart.getId());
        }
    }

    @DeleteMapping("/delete")
    public ShoppingCart deleteShoppingCart(@RequestBody ShoppingCart cart){
        repo.deleteById(cart.getId());
        return cart;
    }
}
