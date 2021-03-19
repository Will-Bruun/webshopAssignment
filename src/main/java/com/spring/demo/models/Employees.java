package com.spring.demo.models;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
public class Employees extends Users{
    private String field;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
