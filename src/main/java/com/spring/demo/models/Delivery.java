package com.spring.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "deliveries")
public class Delivery {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid2")
    @Column(length = 64)
    private String id;

    private String deliveryAddress;
    @ManyToOne
    private ShoppingCart shoppingCart;

    @OneToOne(mappedBy = "delivery")
    @JsonIgnore
    private Payment payment;

    public String getId() {
        return id;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
