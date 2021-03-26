package com.spring.demo.repositories;

import com.spring.demo.models.Deliveries;
import org.springframework.data.repository.CrudRepository;

public interface DeliveryRepository extends CrudRepository<Deliveries, String> {
}
