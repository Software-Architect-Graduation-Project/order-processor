package com.rbittencourt.pa.orderprocessor.application.processor.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbittencourt.pa.orderprocessor.infrastructure.order.EcommerceOrder;
import org.junit.jupiter.api.Test;

class NewOrderListenerTest {

    @Test
    public void should() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.readValue("{ \"clientId\" : \"123\", \"paymentPlan\" : \"10x\", \"products\" : \"453,586,654\" }", EcommerceOrder.class);
    }

}