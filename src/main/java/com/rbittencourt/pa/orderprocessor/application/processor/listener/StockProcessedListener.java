package com.rbittencourt.pa.orderprocessor.application.processor.listener;

import com.rbittencourt.pa.orderprocessor.application.processor.EcommerceOrderProcessor;
import com.rbittencourt.pa.orderprocessor.application.processor.EcommerceOrderUpdate;
import com.rbittencourt.pa.orderprocessor.infrastructure.ecommerceorder.EcommerceOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.rbittencourt.pa.orderprocessor.infrastructure.ecommerceorder.EcommerceOrderStatus.SEPARATING_STOCK;
import static com.rbittencourt.pa.orderprocessor.infrastructure.ecommerceorder.EcommerceOrderStatus.STOCK_SEPARATED;
import static java.time.LocalDateTime.now;

@Component
public class StockProcessedListener {

    @Autowired
    private EcommerceOrderProcessor ecommerceOrderProcessor;

    @KafkaListener(topics = "separating_stock_started", containerFactory = "ecommerceOrderKafkaListenerContainerFactory")
    public void separatingStockStarted(EcommerceOrder ecommerceOrder) {
        EcommerceOrderUpdate ecommerceOrderUpdate = new EcommerceOrderUpdate(ecommerceOrder.getId(), now(), SEPARATING_STOCK);
        ecommerceOrderProcessor.updateOrder(ecommerceOrderUpdate);
    }

    @KafkaListener(topics = "stock_separated", containerFactory = "ecommerceOrderKafkaListenerContainerFactory")
    public void stockSeparated(EcommerceOrder ecommerceOrder) {
        EcommerceOrderUpdate ecommerceOrderUpdate = new EcommerceOrderUpdate(ecommerceOrder.getId(), now(), STOCK_SEPARATED);
        ecommerceOrderProcessor.updateOrder(ecommerceOrderUpdate);
    }

}
