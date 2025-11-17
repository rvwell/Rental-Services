package com.infnet.product.messaging.events;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductCreatedEvent {
    private Long id;
    private Long ownerId;
    private String name;
    private BigDecimal pricePerDay;
}