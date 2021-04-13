package com.spring.demo.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "manufacturers")
public class Manufacturer {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid2")
    @Column(length = 64)
    private String id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Shipment> shipment;

    private String Name;
    private String Info;

    public String getName() {
        return Name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getInfo(){
        return Info;
    }

    public void setName(String name) {
        this.Name = name;
    }


    public void setInfo(String info) {
        Info = info;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Shipment> getShipment() {
        return shipment;
    }

    public void setShipment(List<Shipment> shipment) {
        this.shipment = shipment;
    }
}
