package com.spring.demo.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tags {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.UUIDGenerator")
    @GeneratedValue(generator = "uuid2")
    @Column(length = 64)
    private String id;

    private String tag;

    public String getId() {
        return id;
    }

    public String getTag() {
        return tag;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
