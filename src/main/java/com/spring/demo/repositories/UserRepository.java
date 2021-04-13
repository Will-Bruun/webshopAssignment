package com.spring.demo.repositories;

import com.spring.demo.models.Product;
import com.spring.demo.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {

    @Query(value = "select user from User user where user.firstName like %?1")
    Optional<User> getUserByName(String firstName);
}
