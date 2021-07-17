package com.rbittencourt.pa.orderprocessor.infrastructure.record;

import com.rbittencourt.pa.orderprocessor.infrastructure.ecommerceorder.EcommerceOrderStatus;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "ecommerce_order_record")
public class EcommerceOrderRecord {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column
    private long id;

    @Column(name = "order_id")
    private long orderId;

    @Column
    @Enumerated(EnumType.STRING)
    private EcommerceOrderStatus status;

    @Column(name = "created_on")
    @CreationTimestamp
    private LocalDateTime createdOn;

    public EcommerceOrderRecord() {
    }

    public EcommerceOrderRecord(long orderId, EcommerceOrderStatus status, LocalDateTime createdOn) {
        this.orderId = orderId;
        this.status = status;
        this.createdOn = createdOn;
    }

}
