package com.spring.demo.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.UUIDGenerator")
    @GeneratedValue(generator = "uuid2")
    @Column(length = 64)
    private String id;

    private String Name;
    private Integer Price;
    private Integer Stock;
    @ManyToOne
    private String manufacturerId;

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getName(){
        return Name;
    }

    public void setName(String name){
        this.Name = name;
    }

    public String getManufacturerId() {
        return manufacturerId;
    }

    public Integer getPrice(){
        return Price;
    }

    public void setPrice(Integer price) {
        this.Price = price;
    }

    public Integer getStock(){
        return Stock;
    }

    public void setStock(Integer stock){
        this.Stock = stock;
    }

    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }
}
