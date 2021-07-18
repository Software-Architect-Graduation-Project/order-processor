package com.rbittencourt.pa.orderprocessor.application.processor;

import com.rbittencourt.pa.orderprocessor.infrastructure.ecommerceorder.EcommerceOrder;
import com.rbittencourt.pa.orderprocessor.infrastructure.ecommerceorder.EcommerceOrderRepository;
import com.rbittencourt.pa.orderprocessor.infrastructure.record.EcommerceOrderRecord;
import com.rbittencourt.pa.orderprocessor.infrastructure.record.EcommerceOrderRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class EcommerceOrderProcessor {

    @Autowired
    private EcommerceOrderRepository ecommerceOrderRepository;

    @Autowired
    private EcommerceOrderRecordRepository ecommerceOrderRecordRepository;

    @Autowired
    private KafkaTemplate<String, EcommerceOrder> kafkaTemplate;

    public void createOrder(EcommerceOrder order) {
        ecommerceOrderRepository.save(order);

        EcommerceOrderRecord ecommerceOrderRecord = new EcommerceOrderRecord(order.getId(), order.getStatus(), order.getCreatedOn());
        ecommerceOrderRecordRepository.save(ecommerceOrderRecord);

        sendPaymentReceivedEvent(order);
    }

    private void sendPaymentReceivedEvent(EcommerceOrder order) {
//        Payment payment = new Payment(order.getClientId(), order.getId(), order.getPaymentPlan());
        kafkaTemplate.send("payment_received", order);
    }

    @Transactional
    public void updateOrder(EcommerceOrderUpdate ecommerceOrderUpdate) {
        ecommerceOrderRepository.updateStatus(ecommerceOrderUpdate.getOrderId(), ecommerceOrderUpdate.getNewStatus().name());

        EcommerceOrderRecord ecommerceOrderRecord = new EcommerceOrderRecord(ecommerceOrderUpdate.getOrderId(), ecommerceOrderUpdate.getNewStatus(), ecommerceOrderUpdate.getUpdateTime());
        ecommerceOrderRecordRepository.save(ecommerceOrderRecord);
    }

}
