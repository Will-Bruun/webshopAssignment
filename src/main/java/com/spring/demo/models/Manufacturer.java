package com.spring.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Manufacturer {

    @Id
    @Column(length = 64)
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
