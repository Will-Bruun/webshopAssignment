package com.spring.demo.repositories;

import com.spring.demo.models.Delivery;
import com.spring.demo.models.Payment;
import com.spring.demo.models.Shipment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PaymentRepository extends CrudRepository<Payment, String> {

    //List<Payment> getPaymentsFromDeliveries(List<Delivery> deliveries);

    //List<Payment> getPaymentsFromShipments(List<Shipment> shipments);
}
