package com.spring.demo.controllers;

import com.spring.demo.exceptions.ProductNotFoundException;
import com.spring.demo.models.Product;
import com.spring.demo.models.Tag;
import com.spring.demo.repositories.ProductRepository;
import com.spring.demo.services.TaggingService;
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

    @GetMapping("/index")
    public Iterable<Product> getAllUsers(){
        var userOpt = repo.findAll();
        return userOpt;
    }

    @GetMapping("/searchByName")
    public List<Product> searchProduct(@RequestParam String name) {
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

    @PutMapping ("/addTag")
    public Product addTagToProduct(@RequestParam String id, @RequestBody Tag tag){
        var prod = repo.findById(id).get();
        var updatedProd = TaggingService.appendTag(tag, prod);
        repo.save(updatedProd);
        return updatedProd;
    }

    @PutMapping("/removeTag")
    public Product removeTagFromProduct(@RequestParam String id, @RequestBody Tag tag){
        var prod = repo.findById(id).get();
        var updatedProd = TaggingService.removeTag(tag, prod);
        repo.save(updatedProd);
        return updatedProd;
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
