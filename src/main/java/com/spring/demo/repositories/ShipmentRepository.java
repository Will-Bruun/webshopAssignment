package com.spring.demo.repositories;

import com.spring.demo.models.Manufacturer;
import com.spring.demo.models.Shipment;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ShipmentRepository implements ObjectRepository<Shipment>{

    private Map<String, Shipment> repo;

    public void shipmentRepository() { this.repo = new HashMap<>(); }

    @Override
    public void store(Shipment ship) {
        repo.put(ship.getID(), ship);
    }

    @Override
    public Shipment retrieve(String id) {
        return repo.get(id);
    }

    @Override
    public Shipment search(String name) {
        Collection<Shipment> ships = repo.values();
        for(Shipment ship : ships) {
            if(ship.getCompanyName().equalsIgnoreCase(name)){
                return ship;
            }
        }
        return null;
    }

    @Override
    public Shipment delete(String id) {
        Shipment s = repo.get(id);
        this.repo.remove(id);
        return s;
    }
}
