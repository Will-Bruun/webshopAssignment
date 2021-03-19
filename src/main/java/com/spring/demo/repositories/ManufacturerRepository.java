package com.spring.demo.repositories;

import com.spring.demo.models.Manufacturer;
import org.springframework.data.repository.CrudRepository;

public interface ManufacturerRepository extends CrudRepository<Manufacturer, String> {

}
