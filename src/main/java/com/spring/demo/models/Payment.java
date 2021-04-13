package com.spring.demo.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid2")
    @Column(length = 64)
    private String id;

    private String info;
    @OneToOne
    private Delivery delivery;
    @OneToOne
    private Shipment shipment;
    private LocalDateTime date;

    public void setId(String id) {
        this.id = id;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public String getId() {
        return id;
    }

    public String getInfo() {
        return info;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
