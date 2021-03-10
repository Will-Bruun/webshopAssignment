package com.spring.demo.repositories;

import com.spring.demo.models.Manufacturer;
import com.spring.demo.models.Product;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ManufacturerRepository implements ObjectRepository<Manufacturer>{
    private Map<String, Manufacturer> repo;

    public void manufacturerRepository(){
        this.repo = new HashMap<>();
    }

    @Override
    public void store(Manufacturer man) {
        repo.put(man.getID(), man);
    }

    public void update(String id, Manufacturer man) {
        repo.put(id, man);
    }

    @Override
    public Manufacturer retrieve(String id){
        return repo.get(id);
    }

    @Override
    public Manufacturer search(String name){
        Collection<Manufacturer> mans = repo.values();
        for(Manufacturer man : mans) {
            if(man.getName().equalsIgnoreCase(name)){
                return man;
            }
        }
        return null;
    }

    @Override
    public Manufacturer delete(String id){
        Manufacturer m = repo.get(id);
        this.repo.remove(id);
        return m;
    }

}
