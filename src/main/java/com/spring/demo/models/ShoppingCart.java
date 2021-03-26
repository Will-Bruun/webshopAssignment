package com.spring.demo.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class ShoppingCart {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.UUIDGenerator")
    @GeneratedValue(generator = "uuid2")
    @Column(length = 64)
    private String id;

    @OneToOne
    private String userId;
    // Maybe do it like this? Probably not, don't know if it can work like that
    private String[] productId;
}
