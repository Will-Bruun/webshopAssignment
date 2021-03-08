package com.spring.demo.controllers;

import com.spring.demo.exceptions.ProductNotFoundException;
import com.spring.demo.models.Product;
import com.spring.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository repo;

    @RequestMapping("/rest")
    public String healthCheck() {
        return "OK";
    }

    @GetMapping("/products/get/{id}")
    public Product getProduct(@PathVariable(value = "id") String id){
        return repo.retrieve(id);
    }

    @GetMapping("/products/search/{name}")
    public Product searchProduct(@PathVariable("name") String name){
        return repo.search(name);
    }

    @PostMapping("/products/create")
    public Product createProduct(@RequestBody Product prod){
        repo.store(prod);
        return prod;
    }

    @DeleteMapping("/products/delete/{id}")
    public Product deleteProduct(@PathVariable("id") String id){
        return repo.delete(id);
    }

    //@GetMapping("/products/getAll")
    //public List<Product> getAllProducts(){
      //  return repo.getAll();
    //}
}
