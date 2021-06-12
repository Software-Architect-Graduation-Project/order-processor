package com.rbittencourt.pa.orderprocessor.application.processor;

import com.rbittencourt.pa.orderprocessor.infrastructure.order.OrderStatus;

import java.time.LocalDateTime;

public class OrderUpdate {

    private long orderId;
    private LocalDateTime updateTime;
    private OrderStatus newStatus;

    public OrderUpdate() {
    }

    public OrderUpdate(long orderId, LocalDateTime updateTime, OrderStatus newStatus) {
        this.orderId = orderId;
        this.updateTime = updateTime;
        this.newStatus = newStatus;
    }

    public long getOrderId() {
        return orderId;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public OrderStatus getNewStatus() {
        return newStatus;
    }
    
}
