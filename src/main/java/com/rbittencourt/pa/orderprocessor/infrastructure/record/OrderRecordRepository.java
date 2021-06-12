package com.rbittencourt.pa.orderprocessor.infrastructure.record;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRecordRepository extends JpaRepository<OrderRecord, Long> {

}
