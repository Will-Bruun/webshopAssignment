package com.spring.demo.repositories;

import com.spring.demo.models.Employees;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employees, String> {
}
