package com.infnet.common.valueobject;

import jakarta.validation.constraints.Min;
import lombok.Value;

@Value
public class Money {

    @Min(0)
    Double amount;

    public Money add(Money other) {
        return new Money(this.amount + other.amount);
    }

    public Money multiply(int times) {
        return new Money(this.amount * times);
    }
}
