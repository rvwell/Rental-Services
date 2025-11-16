package com.infnet.common.dto;

import com.infnet.common.enums.ProductCondition;
import com.infnet.common.valueobject.Money;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Data
public class ProductSummary {
    private Long id;
    private String name;
    private Double pricePerDay;
}
