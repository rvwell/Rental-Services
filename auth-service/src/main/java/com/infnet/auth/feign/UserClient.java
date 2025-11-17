package com.infnet.auth.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "user-service", url = "http://user-service:8082", path = "/users")
public interface UserClient {
    @GetMapping("/by-email")
    Map<String, Object> findByEmail(@RequestParam("email") String email);
}