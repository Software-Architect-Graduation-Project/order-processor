package com.rbittencourt.pa.orderprocessor.application.processor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbittencourt.pa.orderprocessor.infrastructure.order.EcommerceOrder;
import com.rbittencourt.pa.orderprocessor.infrastructure.order.OrderRepository;
import com.rbittencourt.pa.orderprocessor.infrastructure.record.OrderRecord;
import com.rbittencourt.pa.orderprocessor.infrastructure.record.OrderRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class OrderProcessor {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderRecordRepository orderRecordRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void createOrder(EcommerceOrder order) throws JsonProcessingException {
        orderRepository.save(order);

        OrderRecord orderRecord = new OrderRecord(order.getId(), order.getStatus(), order.getCreatedOn());
        orderRecordRepository.save(orderRecord);

        sendPaymentReceivedEvent(order);
    }

    private void sendPaymentReceivedEvent(EcommerceOrder order) throws JsonProcessingException {
        Payment payment = new Payment(order.getClientId(), order.getId(), order.getPaymentPlan());
        String payload = objectMapper.writeValueAsString(payment);
        kafkaTemplate.send("payment_received", payload);
    }

    @Transactional
    public void updateOrder(OrderUpdate orderUpdate) {
        orderRepository.updateStatus(orderUpdate.getOrderId(), orderUpdate.getNewStatus().name());

        OrderRecord orderRecord = new OrderRecord(orderUpdate.getOrderId(), orderUpdate.getNewStatus(), orderUpdate.getUpdateTime());
        orderRecordRepository.save(orderRecord);
    }

}
