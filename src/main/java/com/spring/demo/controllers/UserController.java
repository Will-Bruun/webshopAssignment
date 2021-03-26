package com.spring.demo.controllers;

import com.spring.demo.exceptions.EmployeeNotFoundException;
import com.spring.demo.exceptions.UserNotFoundException;
import com.spring.demo.models.Employees;
import com.spring.demo.repositories.UserRepository;
import com.spring.demo.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository repo;

    @Autowired
    public UserController(UserRepository repo){
        this.repo = repo;
    }

    @GetMapping("/get")
    public Users getUser(@RequestParam String id){
        var userOpt = repo.findById(id);
        return userOpt.orElseThrow(() -> new UserNotFoundException(id));
    }

    @GetMapping("/index")
    public Iterable<Users> getAllUsers(){
        var userOpt = repo.findAll();
        return userOpt;
    }

    @PostMapping("/post")
    public Users postUser(@RequestBody Users user){
        repo.save(user);
        return user;
    }

    @PutMapping("/edit")
    public Users editUser(@RequestBody Users user){
        Optional<Users> userInDb = repo.findById(user.getId());
        if (userInDb.isPresent()){
            repo.save(user);
            return user;
        } else {
            throw new EmployeeNotFoundException(user.getId());
        }
    }

    @DeleteMapping("/delete")
    public Users deleteUser(@RequestBody Users user){
        repo.delete(user);
        return user;
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public UserNotFoundException handleUserNotFound(UserNotFoundException e){
        return new UserNotFoundException(e.getMessage());
    }

}
