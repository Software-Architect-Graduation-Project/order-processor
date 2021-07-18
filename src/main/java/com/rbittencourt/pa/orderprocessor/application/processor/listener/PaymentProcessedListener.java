package com.rbittencourt.pa.orderprocessor.application.processor.listener;

import com.rbittencourt.pa.orderprocessor.application.processor.EcommerceOrderProcessor;
import com.rbittencourt.pa.orderprocessor.application.processor.EcommerceOrderUpdate;
import com.rbittencourt.pa.orderprocessor.infrastructure.ecommerceorder.EcommerceOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.rbittencourt.pa.orderprocessor.infrastructure.ecommerceorder.EcommerceOrderStatus.PAYMENT_PROCESSED;
import static com.rbittencourt.pa.orderprocessor.infrastructure.ecommerceorder.EcommerceOrderStatus.PROCESSING_PAYMENT;
import static java.time.LocalDateTime.now;

@Component
public class PaymentProcessedListener {

    @Autowired
    private EcommerceOrderProcessor ecommerceOrderProcessor;

    @KafkaListener(topics = "payment_processing_started", containerFactory = "ecommerceOrderKafkaListenerContainerFactory")
    public void paymentProcessingStarted(EcommerceOrder ecommerceOrder) {
        EcommerceOrderUpdate ecommerceOrderUpdate = new EcommerceOrderUpdate(ecommerceOrder.getId(), now(), PROCESSING_PAYMENT);
        ecommerceOrderProcessor.updateOrder(ecommerceOrderUpdate);
    }

    @KafkaListener(topics = "payment_processed", containerFactory = "ecommerceOrderKafkaListenerContainerFactory")
    public void paymentProcessed(EcommerceOrder ecommerceOrder) {
        EcommerceOrderUpdate ecommerceOrderUpdate = new EcommerceOrderUpdate(ecommerceOrder.getId(), now(), PAYMENT_PROCESSED);
        ecommerceOrderProcessor.updateOrder(ecommerceOrderUpdate);
    }

}
