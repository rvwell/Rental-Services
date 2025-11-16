package com.infnet.common.valueobject;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Value
public class Address {
    @NotBlank String street;
    @NotBlank String city;
    @NotBlank String state;
    @NotBlank String zip;
}
