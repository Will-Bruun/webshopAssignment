package com.spring.demo.controllers;

import com.spring.demo.exceptions.ProductNotFoundException;
import com.spring.demo.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @RequestMapping("/rest")
    public String healthCheck() {
        return "OK";
    }

    @RequestMapping("/products/get")
    public Product getProduct(@RequestParam(value = "id", defaultValue = "Unknown") String id){
        Product product = new Product();
        product.setID(id);
        return product;
    }

}
