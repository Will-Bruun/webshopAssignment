package com.spring.demo.repositories;

import com.spring.demo.models.ChargeRequest;
import org.springframework.data.repository.CrudRepository;

public interface StripeRepository extends CrudRepository<ChargeRequest, String> {
}
