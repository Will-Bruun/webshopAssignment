package com.spring.demo.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "shipment")
public class Shipment {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid2")
    @Column(length = 64)
    private String id;

    private String companyName;
    private String info;

    @ManyToOne(optional = false)
    private Manufacturer manufacturer;

    @ManyToOne(optional = false)
    private Employees employee;

    @OneToOne(mappedBy = "shipment")
    @JsonIgnore
    private Payments payment;

    public String getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getInfo() {
        return info;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    public Payments getPayment() {
        return payment;
    }

    public void setPayment(Payments payment) {
        this.payment = payment;
    }
}
