package com.rbittencourt.pa.orderprocessor.application.processor;

public class Payment {

    private String clientId;
    private long orderId;
    private String paymentPlan;

    public Payment(String clientId, long orderId, String paymentPlan) {
        this.clientId = clientId;
        this.orderId = orderId;
        this.paymentPlan = paymentPlan;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getPaymentPlan() {
        return paymentPlan;
    }

    public void setPaymentPlan(String paymentPlan) {
        this.paymentPlan = paymentPlan;
    }

}
