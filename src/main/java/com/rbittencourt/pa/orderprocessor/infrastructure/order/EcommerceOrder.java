package com.rbittencourt.pa.orderprocessor.infrastructure.order;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "ecommerce_order")
public class EcommerceOrder {

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
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public EcommerceOrder() {
        this.status = OrderStatus.PROCESS_STARTED;
    }

    public EcommerceOrder(String clientId, String paymentPlan, String products) {
        this.clientId = clientId;
        this.paymentPlan = paymentPlan;
        this.products = products;
        this.status = OrderStatus.PROCESS_STARTED;
    }

    public EcommerceOrder(String clientId, String paymentPlan, String products, OrderStatus status) {
        this.clientId = clientId;
        this.paymentPlan = paymentPlan;
        this.products = products;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getPaymentPlan() {
        return paymentPlan;
    }

    public void setPaymentPlan(String paymentPlan) {
        this.paymentPlan = paymentPlan;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

}
