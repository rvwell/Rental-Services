package com.infnet.insurance.controller;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/insurance")
public class InsuranceController {
    @PostMapping("/quote")
    public ResponseEntity<Map<String, Object>> quote(@RequestBody QuoteRequest req) {
        BigDecimal premium = req.getRentalValue().multiply(BigDecimal.valueOf(0.1));
        return ResponseEntity.ok(Map.of("premium", premium, "currency", "USD"));
    }

    @Data
    public static class QuoteRequest {
        private BigDecimal rentalValue;
    }
}