package com.infnet.rental.controller;

import com.infnet.rental.domain.model.RentalRequest;
import com.infnet.rental.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/rentals")
@RequiredArgsConstructor
public class RentalController {
    private final RentalService service;

    @PostMapping
    public ResponseEntity<RentalRequest> request(@RequestBody RentalRequest req) {
        return ResponseEntity.ok(service.request(req));
    }

    @GetMapping("/by-renter/{renterId}")
    public ResponseEntity<List<RentalRequest>> listByRenter(@PathVariable Long renterId) {
        return ResponseEntity.ok(service.listByRenter(renterId));
    }

    @GetMapping("/by-renter/{renterId}/products")
    public ResponseEntity<List<Map<String, Object>>> listProductsByRenter(@PathVariable Long renterId) {
        return ResponseEntity.ok(service.listProductsByRenter(renterId));
    }
}