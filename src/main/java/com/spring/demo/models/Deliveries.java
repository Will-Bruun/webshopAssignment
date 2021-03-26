package com.spring.demo.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Deliveries {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.UUIDGenerator")
    @GeneratedValue(generator = "uuid2")
    @Column(length = 64)
    private String id;

    private String deliveryAddress;
    private String shoppingCartId;

    public String getId() {
        return id;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getShoppingCartId() {
        return shoppingCartId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void setShoppingCartId(String shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }
}
