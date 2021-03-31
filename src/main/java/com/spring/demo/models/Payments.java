package com.spring.demo.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "payment")
public class Payments {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid2")
    @Column(length = 64)
    private String id;

    private String deliveryAdress;
    @OneToOne
    private Deliveries delivery;
    @OneToOne
    private Shipment shipment;


    public void setId(String id) {
        this.id = id;
    }

    public void setDeliveryAdress(String deliveryAdress) {
        this.deliveryAdress = deliveryAdress;
    }

    public void setDelivery(Deliveries delivery) {
        this.delivery = delivery;
    }

    public String getId() {
        return id;
    }

    public String getDeliveryAdress() {
        return deliveryAdress;
    }

    public Deliveries getDelivery() {
        return delivery;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }
}
