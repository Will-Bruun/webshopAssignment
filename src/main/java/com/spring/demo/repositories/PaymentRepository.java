package com.spring.demo.repositories;

import com.spring.demo.models.Delivery;
import com.spring.demo.models.Payment;
import com.spring.demo.models.Shipment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PaymentRepository extends CrudRepository<Payment, String> {

    Payment getPaymentByDelivery(Delivery delivery);

    Payment getPaymentByShipment(Shipment shipment);
}
