package com.rbittencourt.pa.orderprocessor.application.processor.listener;

import com.rbittencourt.pa.orderprocessor.application.processor.EcommerceOrderProcessor;
import com.rbittencourt.pa.orderprocessor.application.processor.EcommerceOrderUpdate;
import com.rbittencourt.pa.orderprocessor.infrastructure.ecommerceorder.EcommerceOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.rbittencourt.pa.orderprocessor.infrastructure.ecommerceorder.EcommerceOrderStatus.ORGANIZING_LOGISTICS;
import static com.rbittencourt.pa.orderprocessor.infrastructure.ecommerceorder.EcommerceOrderStatus.READY_TO_DELIVERY;
import static java.time.LocalDateTime.now;

@Component
public class LogisticsProcessedListener {

    @Autowired
    private EcommerceOrderProcessor ecommerceOrderProcessor;

    @KafkaListener(topics = "organizing_logistics_started", containerFactory = "ecommerceOrderKafkaListenerContainerFactory")
    public void separatingStockStarted(EcommerceOrder ecommerceOrder) {
        EcommerceOrderUpdate ecommerceOrderUpdate = new EcommerceOrderUpdate(ecommerceOrder.getId(), now(), ORGANIZING_LOGISTICS);
        ecommerceOrderProcessor.updateOrder(ecommerceOrderUpdate);
    }

    @KafkaListener(topics = "ready_to_delivery", containerFactory = "ecommerceOrderKafkaListenerContainerFactory")
    public void stockSeparated(EcommerceOrder ecommerceOrder) {
        EcommerceOrderUpdate ecommerceOrderUpdate = new EcommerceOrderUpdate(ecommerceOrder.getId(), now(), READY_TO_DELIVERY);
        ecommerceOrderProcessor.updateOrder(ecommerceOrderUpdate);
    }

}
