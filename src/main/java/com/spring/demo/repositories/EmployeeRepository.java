package com.spring.demo.repositories;

import com.spring.demo.models.Employee;
import com.spring.demo.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee, String> {

    @Query(value = "select employee from Employee employee where employee.firstName like %?1")
    List<Employee> getEmployeeByName(String firstName);
}
