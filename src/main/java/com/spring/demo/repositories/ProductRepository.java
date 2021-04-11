package com.spring.demo.repositories;

import com.spring.demo.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, String> {

    @Query(value = "select products from Product products where products.Name like %?1")
    Optional<Product> getProductByName(String productName);
}
