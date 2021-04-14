package com.spring.demo.services;

import com.spring.demo.models.Product;
import com.spring.demo.models.ShoppingCart;

import java.util.List;

public class CartService {

    public static ShoppingCart appendProduct(Product prod, ShoppingCart cart){
        List<Product> products = cart.getProducts();
        products.add(prod);
        cart.setProducts(products);
        return cart;
    }

    public static ShoppingCart removeProduct(Product prod, ShoppingCart cart){
        List<Product> products = cart.getProducts();
        products.remove(prod);
        cart.setProducts(products);
        return cart;
    }

    public static ShoppingCart removeAllProducts(ShoppingCart cart){
        List<Product> products = cart.getProducts();
        if(products.isEmpty()){
            return cart;
        } else {
            products.removeAll(products);
        }
        cart.setProducts(products);
        return cart;
    }
}
