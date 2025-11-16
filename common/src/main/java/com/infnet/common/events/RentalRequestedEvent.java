package com.infnet.common.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalRequestedEvent {
    private Long rentalId;
    private Long userId;
    private Long productId;
}
