package com.rbittencourt.pa.orderprocessor.infrastructure.order;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "order_requested")
public class Order {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column
    private long id;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "created_on")
    @CreationTimestamp
    private LocalDateTime createdOn;

    @Column(name = "payment_plan")
    private String paymentPlan;

    @Column
    private String products;

    @Column
    private OrderStatus status;

    public Order() {
    }

    public Order(String clientId, String paymentPlan, String products, OrderStatus status) {
        this.clientId = clientId;
        this.paymentPlan = paymentPlan;
        this.products = products;
        this.status = status;
    }

}
