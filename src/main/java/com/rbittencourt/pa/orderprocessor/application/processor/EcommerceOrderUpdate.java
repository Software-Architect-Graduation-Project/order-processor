package com.rbittencourt.pa.orderprocessor.application.processor;

import com.rbittencourt.pa.orderprocessor.infrastructure.ecommerceorder.EcommerceOrderStatus;

import java.time.LocalDateTime;

public class EcommerceOrderUpdate {

    private long orderId;
    private LocalDateTime updateTime;
    private EcommerceOrderStatus newStatus;

    public EcommerceOrderUpdate() {
    }

    public EcommerceOrderUpdate(long orderId, LocalDateTime updateTime, EcommerceOrderStatus newStatus) {
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

    public EcommerceOrderStatus getNewStatus() {
        return newStatus;
    }
    
}
