package com.infnet.rental.service;

import com.infnet.rental.domain.model.RentalRequest;
import com.infnet.rental.messaging.events.RentalRequestedEvent;
import com.infnet.rental.repository.RentalRequestRepository;
import com.infnet.rental.feign.ProductClient;
import com.infnet.rental.feign.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentalService {
    private final RentalRequestRepository repository;
    private final ProductClient productClient;
    private final UserClient userClient;
    private final StreamBridge streamBridge;

    public RentalRequest request(RentalRequest req) {
        Map<String, Object> product = productClient.get(req.getProductId());
        Map<String, Object> renter = userClient.get(req.getRenterId());
        if (product == null || renter == null) throw new IllegalArgumentException("invalid_ids");
        req.setStatus("PENDING");
        RentalRequest saved = repository.save(req);
        RentalRequestedEvent event = new RentalRequestedEvent();
        event.setRentalId(saved.getId());
        event.setProductId(saved.getProductId());
        event.setRenterId(saved.getRenterId());
        event.setStartDate(saved.getStartDate());
        event.setEndDate(saved.getEndDate());
        streamBridge.send("rentalRequested-out-0", event);
        return saved;
    }

    public RentalRequest approve(Long rentalId) {
        RentalRequest rr = repository.findById(rentalId).orElseThrow();
        rr.setStatus("APPROVED");
        return repository.save(rr);
    }

    public List<RentalRequest> listByRenter(Long renterId) {
        return repository.findByRenterId(renterId);
    }

    public List<Map<String, Object>> listProductsByRenter(Long renterId) {
        return repository.findByRenterId(renterId).stream()
            .map(rr -> productClient.get(rr.getProductId()))
            .filter(p -> p != null)
            .collect(Collectors.toList());
    }
}