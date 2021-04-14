package com.spring.demo.controllers;

import com.spring.demo.exceptions.EmployeeNotFoundException;
import com.spring.demo.exceptions.UserNotFoundException;

import com.spring.demo.models.ShoppingCart;
import com.spring.demo.repositories.ShoppingCartRepository;
import com.spring.demo.repositories.UserRepository;
import com.spring.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepo;
    private final ShoppingCartRepository cartRepo;

    @Autowired
    public UserController(UserRepository userRepo, ShoppingCartRepository cartRepo){
        this.userRepo = userRepo;
        this.cartRepo = cartRepo;
    }

    @GetMapping("/get")
    public User getUser(@RequestParam String id){
        var userOpt = userRepo.findById(id);
        return userOpt.orElseThrow(() -> new UserNotFoundException(id));
    }

    @GetMapping("/index")
    public Iterable<User> getAllUsers(){
        var userOpt = repo.findAll();
        return userOpt;
    }

    @GetMapping("/searchByName")
    public User searchUser(@RequestParam String name){
        return repo.getUserByName(name).orElseThrow( () -> new UserNotFoundException("name" + name));
    }

    @GetMapping("/getName")
    public String getNameById(@RequestParam String id){
        var userOpt = repo.findById(id);
        User user = userOpt.get();
        return user.getName();
    }

    @PostMapping("/post")
    public User postUser(@RequestBody User user){
        repo.save(user);
        user.setCart(cart);
        cart.setUser(user);
        userRepo.save(user);
        cartRepo.save(cart);
        return user;
    }

    @PutMapping("/edit")
    public User editUser(@RequestBody User user){
        Optional<User> userInDb = repo.findById(user.getId());
        if (userInDb.isPresent()){
            repo.save(user);
            return user;
        } else {
            throw new EmployeeNotFoundException(user.getId());
        }
    }

    @DeleteMapping("/delete")
    public User deleteUser(@RequestBody User user){
        repo.delete(user);
        return user;
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public UserNotFoundException handleUserNotFound(UserNotFoundException e){
        return new UserNotFoundException(e.getMessage());
    }

}
