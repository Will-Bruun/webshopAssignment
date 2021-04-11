package com.spring.demo.repositories;

import com.spring.demo.models.Delivery;
import org.springframework.data.repository.CrudRepository;

public interface DeliveryRepository extends CrudRepository<Delivery, String> {
}
