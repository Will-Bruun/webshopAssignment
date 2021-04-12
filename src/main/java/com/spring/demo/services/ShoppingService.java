package com.spring.demo.services;

import com.spring.demo.models.Delivery;
import com.spring.demo.models.ShoppingCart;

public class ShoppingService {

    public static Delivery createDelivery(String adress, ShoppingCart cart){
        Delivery deliv = new Delivery();
        deliv.setDeliveryAddress(adress);
        deliv.setShoppingCart(cart);

        return deliv;
    }

    public static ShoppingCart connectDelivery(ShoppingCart cart, Delivery deliv){
        cart.appendDelivery(deliv);
        return cart;
    }
}
