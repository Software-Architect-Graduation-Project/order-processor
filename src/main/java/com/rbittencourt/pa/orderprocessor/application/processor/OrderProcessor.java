package com.rbittencourt.pa.orderprocessor.application.processor;

import com.rbittencourt.pa.orderprocessor.infrastructure.order.EcommerceOrder;
import com.rbittencourt.pa.orderprocessor.infrastructure.order.OrderRepository;
import com.rbittencourt.pa.orderprocessor.infrastructure.record.OrderRecord;
import com.rbittencourt.pa.orderprocessor.infrastructure.record.OrderRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class OrderProcessor {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderRecordRepository orderRecordRepository;

    public void createOrder(EcommerceOrder order) {
        orderRepository.save(order);

        OrderRecord orderRecord = new OrderRecord(order.getId(), order.getStatus(), order.getCreatedOn());
        orderRecordRepository.save(orderRecord);
    }

    @Transactional
    public void updateOrder(OrderUpdate orderUpdate) {
        orderRepository.updateStatus(orderUpdate.getOrderId(), orderUpdate.getNewStatus().name());

        OrderRecord orderRecord = new OrderRecord(orderUpdate.getOrderId(), orderUpdate.getNewStatus(), orderUpdate.getUpdateTime());
        orderRecordRepository.save(orderRecord);
    }

}
