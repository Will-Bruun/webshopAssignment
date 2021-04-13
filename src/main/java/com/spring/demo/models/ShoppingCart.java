package com.spring.demo.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
    private User user;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Product> products;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Delivery> deliveries;

    public String getId() {
        return id;
    }

    public String getUserId() {
        return user.getId();
    }

    public User getUser(){
        return user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public void appendDelivery(Delivery delivery){
        this.deliveries.add(delivery);
    }

    public void appendItemToProducts(Product product){
        this.products.add(product);
    }

    public void removeItemFromProducts(Product product){
        this.products.remove(product);
    }

    public void removeAll(){
        List<Product> list = this.getProducts();
        this.products.removeAll(list);
    }

}
