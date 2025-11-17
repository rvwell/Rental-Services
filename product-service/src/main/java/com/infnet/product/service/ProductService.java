package com.infnet.product.service;

import com.infnet.product.domain.model.Product;
import com.infnet.product.messaging.events.ProductCreatedEvent;
import com.infnet.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final StreamBridge streamBridge;

    public Product create(Product product) {
        Product saved = repository.save(product);
        ProductCreatedEvent event = new ProductCreatedEvent();
        event.setId(saved.getId());
        event.setOwnerId(saved.getOwnerId());
        event.setName(saved.getName());
        event.setPricePerDay(saved.getPricePerDay());
        streamBridge.send("productCreated-out-0", event);
        return saved;
    }

    public Product get(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Product> list() {
        return repository.findAll();
    }
}