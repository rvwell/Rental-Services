package com.infnet.rental.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "user-service", path = "/users")
public interface UserClient {
    @GetMapping("/{id}")
    Map<String, Object> get(@PathVariable("id") Long id);
}