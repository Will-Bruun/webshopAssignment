package com.spring.demo.controllers;

import com.spring.demo.exceptions.EmployeeNotFoundException;
import com.spring.demo.models.Employees;
import com.spring.demo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EmployeeController {

    private final EmployeeRepository repo;

    @Autowired
    public EmployeeController(EmployeeRepository repo){
        this.repo = repo;
    }

    @GetMapping("/employee/get")
    public Employees getEmployee(@RequestParam String id){
        var userOpt = repo.findById(id);
        return userOpt.orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @GetMapping("/employee/index")
    public Iterable<Employees> getAllEmployees(){
        var userOpt = repo.findAll();
        return userOpt;
    }

    @PostMapping("/employee/post")
    public Employees createEmployee(@RequestBody Employees emp){
        repo.save(emp);
        return emp;
    }

    @PutMapping("/employee/edit")
    public Employees editEmployee(@RequestBody Employees emp){
        Optional<Employees> employeeInDb = repo.findById(emp.getId());
        if (employeeInDb.isPresent()){
            repo.save(emp);
            return emp;
        } else {
            throw new EmployeeNotFoundException(emp.getId());
        }
    }

    @DeleteMapping("/employee/delete")
    public Employees deleteEmployee(@RequestBody Employees emp){
        repo.deleteById(emp.getId());
        return emp;
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public EmployeeNotFoundException handleEmployeeNotFound(EmployeeNotFoundException e){
        return new EmployeeNotFoundException(e.getMessage());
    }
}
