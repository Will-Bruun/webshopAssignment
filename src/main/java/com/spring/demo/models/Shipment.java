package com.spring.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Shipment {

    @Id
    @Column(length = 64)
    private String ID;

    private String companyName;
    private String info;
    private String manufacturerID;
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
