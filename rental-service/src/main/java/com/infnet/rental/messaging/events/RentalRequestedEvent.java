package com.infnet.rental.messaging.events;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RentalRequestedEvent {
    private Long rentalId;
    private Long productId;
    private Long renterId;
    private LocalDate startDate;
    private LocalDate endDate;
}