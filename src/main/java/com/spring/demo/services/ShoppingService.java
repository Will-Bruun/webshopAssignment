package com.spring.demo.services;

import com.spring.demo.models.Delivery;
import com.spring.demo.models.Product;
import com.spring.demo.models.ShoppingCart;

import java.util.List;

public class ShoppingService {

    public static Delivery createDelivery(String adress, ShoppingCart cart){
        Delivery deliv = new Delivery();
        deliv.setDeliveryAddress(adress);
        deliv.setShoppingCart(cart);

        return deliv;
    }

    public static ShoppingCart connectDelivery(ShoppingCart cart, Delivery deliv){
        List<Delivery> delivs = cart.getDeliveries();
        delivs.add(deliv);
        cart.setDeliveries(delivs);
        return cart;
    }

    public static double summarizeCost(ShoppingCart cart){
        double sum = 0;
        List<Product> prods = cart.getProducts();
        for (Product prod : prods){
            sum += prod.getPrice();
        }
        return sum;
    }
}
