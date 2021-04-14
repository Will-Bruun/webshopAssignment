package com.spring.demo.repositories;

import com.spring.demo.models.Shipment;
import org.springframework.data.repository.CrudRepository;

public interface ShipmentRepository extends CrudRepository<Shipment, String> {

}
