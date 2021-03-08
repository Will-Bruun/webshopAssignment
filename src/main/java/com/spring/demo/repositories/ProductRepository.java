package com.spring.demo.repositories;

import com.spring.demo.models.Product;


import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepository implements ObjectRepository<Product>{
    private Map<String, Product> repo;

    public void productRepository() {
        this.repo = new HashMap<>();
    }

    @Override
    public void store(Product prod){
        repo.put(prod.getID(), prod);
    }

    @Override
    public Product retrieve(String id){
        return repo.get(id);
    }

    //public List<Map<String, Product>> getAll() {
      //  return repo;
    //}

    @Override
    public Product search(String name){
        Collection<Product> prods = repo.values();
        for(Product prod : prods) {
            if(prod.getName().equalsIgnoreCase(name)){
                return prod;
            }
        }
        return null;
    }

    @Override
    public Product delete(String id){
        Product p = repo.get(id);
        this.repo.remove(id);
        return p;
    }
}
