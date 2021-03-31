package com.spring.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shoppingCart")
public class ShoppingCart {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid2")
    @Column(length = 64)
    private String id;

    @OneToOne
    private Users user;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Product> products;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Deliveries> deliveries;

    public String getId() {
        return id;
    }

    public String getUserId() {
        return user.getId();
    }

    public Users getUser(){
        return user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Deliveries> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<Deliveries> deliveries) {
        this.deliveries = deliveries;
    }
}
