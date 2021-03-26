package com.spring.demo.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Payments {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "org-hibernateUUIDGenerator")
    @GeneratedValue(generator = "uuid2")
    @Column(length = 64)
    private String id;

    private String deliveryAdress;
    private String cartId;

    public void setId(String id) {
        this.id = id;
    }

    public void setDeliveryAdress(String deliveryAdress) {
        this.deliveryAdress = deliveryAdress;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getId() {
        return id;
    }

    public String getDeliveryAdress() {
        return deliveryAdress;
    }

    public String getCartId() {
        return cartId;
    }
}
