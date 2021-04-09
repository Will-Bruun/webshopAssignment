package com.spring.demo.controllers;

import com.spring.demo.exceptions.EmployeeNotFoundException;
import com.spring.demo.exceptions.UserNotFoundException;
import com.spring.demo.models.ShoppingCart;
import com.spring.demo.repositories.ShoppingCartRepository;
import com.spring.demo.repositories.UserRepository;
import com.spring.demo.models.Users;
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
    public Users getUser(@RequestParam String id){
        var userOpt = userRepo.findById(id);
        return userOpt.orElseThrow(() -> new UserNotFoundException(id));
    }

    @GetMapping("/index")
    public Iterable<Users> getAllUsers(){
        var userOpt = userRepo.findAll();
        return userOpt;
    }

    @PostMapping("/post")
    public Users postUser(@RequestBody Users user, @RequestBody ShoppingCart cart){
        user.setCart(cart);
        cart.setUser(user);
        userRepo.save(user);
        cartRepo.save(cart);
        return user;
    }

    @PutMapping("/edit")
    public Users editUser(@RequestBody Users user){
        Optional<Users> userInDb = userRepo.findById(user.getId());
        if (userInDb.isPresent()){
            userRepo.save(user);
            return user;
        } else {
            throw new EmployeeNotFoundException(user.getId());
        }
    }

    @DeleteMapping("/delete")
    public Users deleteUser(@RequestBody Users user){
        userRepo.delete(user);
        return user;
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public UserNotFoundException handleUserNotFound(UserNotFoundException e){
        return new UserNotFoundException(e.getMessage());
    }

}
