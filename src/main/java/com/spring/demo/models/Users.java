package com.spring.demo.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Users extends Humans {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.UUIDGenerator")
    @GeneratedValue(generator = "uuid2")
    @Column(length = 64)
    @OneToOne
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
