package com.spring.demo.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Shipment {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.UUIDGenerator")
    @GeneratedValue(generator = "uuid2")
    @Column(length = 64)
    private String ID;

    private String companyName;
    private String info;
    @ManyToOne(optional = false)
    private String manufacturerID;
    @ManyToOne(optional = false)
    private String EmployeeID;

    public String getID() {
        return ID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getInfo() {
        return info;
    }

    public String getManufacturerID() {
        return manufacturerID;
    }

    public String getEmployeeID() {
        return EmployeeID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setManufacturerID(String manufacturerID) {
        this.manufacturerID = manufacturerID;
    }

    public void setEmployeeID(String employeeID) {
        EmployeeID = employeeID;
    }
}
