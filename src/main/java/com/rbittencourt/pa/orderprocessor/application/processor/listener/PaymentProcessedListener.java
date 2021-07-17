package com.rbittencourt.pa.orderprocessor.application.processor.listener;

import com.rbittencourt.pa.orderprocessor.application.processor.EcommerceOrderProcessor;
import com.rbittencourt.pa.orderprocessor.application.processor.EcommerceOrderUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentProcessedListener {

    @Autowired
    private EcommerceOrderProcessor ecommerceOrderProcessor;

    @KafkaListener(topics = "payment_processed", containerFactory = "orderUpdateKafkaListenerContainerFactory")
    public void paymentProcessed(EcommerceOrderUpdate update) {
        ecommerceOrderProcessor.updateOrder(update);
    }

    @KafkaListener(topics = "payment_processing_started", containerFactory = "orderUpdateKafkaListenerContainerFactory")
    public void paymentProcessingStarted(EcommerceOrderUpdate update) {
        ecommerceOrderProcessor.updateOrder(update);
    }

}
