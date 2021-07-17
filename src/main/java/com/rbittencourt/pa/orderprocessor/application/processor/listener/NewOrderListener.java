package com.rbittencourt.pa.orderprocessor.application.processor.listener;

import com.rbittencourt.pa.orderprocessor.application.processor.EcommerceOrderProcessor;
import com.rbittencourt.pa.orderprocessor.infrastructure.ecommerceorder.EcommerceOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NewOrderListener {

    @Autowired
    private EcommerceOrderProcessor ecommerceOrderProcessor;

    @KafkaListener(topics = "new_ecommerce_order", containerFactory = "ecommerceOrderKafkaListenerContainerFactory")
    public void receiveNewOrder(EcommerceOrder order) {
        ecommerceOrderProcessor.createOrder(order);
    }

}
