package com.spring.demo.controllers;

import com.spring.demo.exceptions.ProductNotFoundException;
import com.spring.demo.models.Product;
import com.spring.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    private final ProductRepository repo;

    @Autowired
    public ProductController(ProductRepository repo){
        this.repo = repo;
    }

    @RequestMapping("/rest")
    public String healthCheck() {
        return "OK";
    }

    @GetMapping("/get")
    public Product getProduct(@RequestParam String id){
        var userOpt = repo.findById(id);
        return userOpt.orElseThrow(() -> new ProductNotFoundException("id" + id));
    }

    @GetMapping("/searchByName")
    public List <Product> searchProduct(@RequestParam String name) {
        try {
            return repo.getProductByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ProductNotFoundException("name" + name);
        }
    }


    @PostMapping("/create")
    public Product createProduct(@RequestBody Product prod){
        repo.save(prod);
        return prod;
    }

    @PutMapping("/edit")
    public Product editProduct(@RequestBody Product prod){
        repo.save(prod);
        return prod;
    }

    @DeleteMapping("/delete")
    public Product deleteProduct(@RequestBody Product prod){
        repo.deleteById(prod.getId());
        return prod;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProductNotFoundException handleProductNotFound(ProductNotFoundException e) {
        return new ProductNotFoundException(e.getMessage());
    }

}
