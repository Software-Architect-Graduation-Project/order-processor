package com.rbittencourt.pa.orderprocessor.infrastructure.ecommerceorder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface EcommerceOrderRepository extends JpaRepository<EcommerceOrder, Long> {

    @Modifying
    @Query(value = "UPDATE ecommerce_order SET status = ?2 WHERE id = ?1", nativeQuery = true)
    void updateStatus(long orderId, String orderStatus);

}
