package com.rbittencourt.pa.orderprocessor.application.processor.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbittencourt.pa.orderprocessor.application.processor.OrderProcessor;
import com.rbittencourt.pa.orderprocessor.infrastructure.order.EcommerceOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NewOrderListener {

    @Autowired
    private OrderProcessor orderProcessor;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "new_order", groupId = "foo")
    public void receiveNewOrder(String message) throws JsonProcessingException {
        EcommerceOrder order = objectMapper.readValue(message, EcommerceOrder.class);
        orderProcessor.createOrder(order);
    }

}
