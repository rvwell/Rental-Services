package com.infnet.rental.messaging.commands;

import lombok.Data;

@Data
public class ApproveRentalCommand {
    private Long rentalId;
}