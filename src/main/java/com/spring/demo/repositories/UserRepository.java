package com.spring.demo.repositories;

import com.spring.demo.models.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, String> {
}
