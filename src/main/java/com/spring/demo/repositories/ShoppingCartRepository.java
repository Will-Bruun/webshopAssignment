package com.spring.demo.repositories;

import com.spring.demo.models.ShoppingCart;
import org.springframework.data.repository.CrudRepository;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, String> {
}
