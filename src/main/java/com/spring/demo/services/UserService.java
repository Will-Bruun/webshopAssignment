package com.spring.demo.services;

import com.spring.demo.models.ShoppingCart;
import com.spring.demo.models.User;

public class UserService {

    public static ShoppingCart createCart(User user){
        ShoppingCart cart = new ShoppingCart();
        cart.setUser(user);
        return cart;
    }
}
