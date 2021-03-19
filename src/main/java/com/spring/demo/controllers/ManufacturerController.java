package com.spring.demo.controllers;


import com.spring.demo.exceptions.ManufacturerNotFoundException;
import com.spring.demo.models.Manufacturer;
import com.spring.demo.models.Product;
import com.spring.demo.repositories.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ManufacturerController {

    private final ManufacturerRepository repo;

    @Autowired
    public ManufacturerController(ManufacturerRepository repo){
        this.repo = repo;
    }

    @GetMapping("/manufacturer/get")
    public Manufacturer getManufacturer(@RequestParam String id){
        var userOpt = repo.findById(id);
        return userOpt.orElseThrow(() -> new ManufacturerNotFoundException(id));
    }

    @GetMapping("/manufacturer/index")
    public Iterable<Manufacturer> getAllManufacturer(){
        var userOpt = repo.findAll();
        return userOpt;
    }

    @PostMapping("/manufacturer/create")
    public Manufacturer createManufacturer(@RequestBody Manufacturer man){
        repo.save(man);
        return man;
    }

    @PutMapping("/manufacturer/edit")
    public Manufacturer editManufacturer(@RequestBody Manufacturer man){
        Optional<Manufacturer> manufacturerInDb = repo.findById(man.getID());
        if (manufacturerInDb.isPresent()){
            repo.save(man);
            return man;
        } else {
            throw new ManufacturerNotFoundException(man.getID());
        }

    }

    @DeleteMapping("/manufacturer/delete")
    public Manufacturer deleteManufacturer(@RequestBody Manufacturer man){
        repo.deleteById(man.getID());
        return man;
    }

    @ExceptionHandler(ManufacturerNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ManufacturerNotFoundException handleManufacturerNotFound(ManufacturerNotFoundException e){
        return new ManufacturerNotFoundException(e.getMessage());
    }

}
