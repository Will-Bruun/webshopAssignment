package com.spring.demo.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Manufacturer {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.UUIDGenerator")
    @GeneratedValue(generator = "uuid2")
    @Column(length = 64)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private String ID;

    private String Name;
    private String Info;

    public String getName() {
        return Name;
    }

    public String getID(){
        return ID;
    }

    public String getInfo(){
        return Info;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setInfo(String info) {
        Info = info;
    }
}
