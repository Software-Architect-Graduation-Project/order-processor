package com.rbittencourt.pa.orderprocessor.infrastructure.record;

import com.rbittencourt.pa.orderprocessor.infrastructure.order.OrderStatus;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "order_record")
public class OrderRecord {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column
    private long id;

    @Column(name = "order_id")
    private long orderId;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "created_on")
    @CreationTimestamp
    private LocalDateTime createdOn;

    public OrderRecord() {
    }

    public OrderRecord(long orderId, OrderStatus status, LocalDateTime createdOn) {
        this.orderId = orderId;
        this.status = status;
        this.createdOn = createdOn;
    }

}
