package com.spring.demo.controllers;

import com.spring.demo.exceptions.EmployeeNotFoundException;
import com.spring.demo.exceptions.UserNotFoundException;
import com.spring.demo.models.Employee;
import com.spring.demo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeRepository repo;

    @Autowired
    public EmployeeController(EmployeeRepository repo){
        this.repo = repo;
    }

    @GetMapping("/get")
    public Employee getEmployee(@RequestParam String id){
        var userOpt = repo.findById(id);
        return userOpt.orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @GetMapping("/index")
    public Iterable<Employee> getAllEmployees(){
        var userOpt = repo.findAll();
        return userOpt;
    }

    @GetMapping("/searchByName")
    public List<Employee> searchProduct(@RequestParam String name) {
        try {
            return repo.getEmployeeByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            throw new EmployeeNotFoundException("name" + name);
        }
    }

    @GetMapping("/getName")
    public String getNameById(@RequestParam String id){
        var userOpt = repo.findById(id);
        Employee employee = userOpt.get();
        return employee.getName();
    }

    @PostMapping("/create")
    public Employee createEmployee(@RequestBody Employee emp){
        repo.save(emp);
        return emp;
    }

    @PutMapping("/edit")
    public Employee editEmployee(@RequestBody Employee emp){
        Optional<Employee> employeeInDb = repo.findById(emp.getId());
        if (employeeInDb.isPresent()){
            repo.save(emp);
            return emp;
        } else {
            throw new EmployeeNotFoundException(emp.getId());
        }
    }

    @DeleteMapping("/delete")
    public Employee deleteEmployee(@RequestBody Employee emp){
        repo.deleteById(emp.getId());
        return emp;
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public EmployeeNotFoundException handleEmployeeNotFound(EmployeeNotFoundException e){
        return new EmployeeNotFoundException(e.getMessage());
    }
}
