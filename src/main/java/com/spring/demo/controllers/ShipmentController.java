package com.spring.demo.controllers;

import com.spring.demo.exceptions.ShipmentNotFoundException;
import com.spring.demo.models.Shipment;
import com.spring.demo.repositories.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ShipmentController {

    private final ShipmentRepository repo;

    @Autowired
    public ShipmentController(ShipmentRepository repo){
        this.repo = repo;
    }

    @GetMapping("/shipment/get")
    public Shipment getShipment(@RequestParam String id){
        var userOpt = repo.findById(id);
        return userOpt.orElseThrow(() -> new ShipmentNotFoundException(id));
    }

    @GetMapping("/shipment/index")
    public Iterable<Shipment> getAllShipments(){
        var userOpt = repo.findAll();
        return userOpt;
    }

    @PostMapping("/shipment/create")
    public Shipment createShipment(@RequestBody Shipment ship){
        repo.save(ship);
        return ship;
    }

    @PutMapping("/shipment/edit")
    public Shipment editShipment(@RequestBody Shipment ship){
        Optional<Shipment> shipmentInDb = repo.findById(ship.getID());
        if (shipmentInDb.isPresent()){
            repo.save(ship);
            return ship;
        } else {
            throw new ShipmentNotFoundException(ship.getID());
        }

    }

    @DeleteMapping("/shipment/delete")
    public Shipment deleteShipment(@RequestBody Shipment ship){
        repo.deleteById(ship.getID());
        return ship;
    }

    @ExceptionHandler(ShipmentNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ShipmentNotFoundException handleShipmentNotFound(ShipmentNotFoundException e){
        return new ShipmentNotFoundException(e.getMessage());
    }

}
