package com.rbittencourt.pa.orderprocessor.infrastructure.order;

public enum OrderStatus {

    PROCESS_STARTED,
    PROCESSING_PAYMENT,
    PAYMENT_PROCESSED,
    SEPARATING_STOCK,
    STOCK_SEPARATED,
    ORGANIZING_LOGISTIC,
    READY_TO_DELIVERY

}
