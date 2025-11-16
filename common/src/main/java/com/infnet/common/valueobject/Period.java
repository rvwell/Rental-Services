package com.infnet.common.valueobject;

import lombok.Value;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Value
public class Period {
    LocalDate start;
    LocalDate end;

    public boolean isValid() {
        return start.isBefore(end);
    }
}
