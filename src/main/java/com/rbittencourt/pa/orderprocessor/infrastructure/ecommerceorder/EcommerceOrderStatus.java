package com.rbittencourt.pa.orderprocessor.infrastructure.ecommerceorder;

public enum EcommerceOrderStatus {

    PROCESS_STARTED,
    PROCESSING_PAYMENT,
    PAYMENT_PROCESSED,
    SEPARATING_STOCK,
    STOCK_SEPARATED,
    ORGANIZING_LOGISTIC,
    READY_TO_DELIVERY

}
