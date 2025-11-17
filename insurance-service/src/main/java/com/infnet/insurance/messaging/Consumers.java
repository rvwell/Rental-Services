package com.infnet.insurance.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.function.Consumer;

@Configuration
@Slf4j
public class Consumers {
    @Bean
    public Consumer<Map<String, Object>> rentalEvents() {
        return event -> log.info("rental event received: {}", event);
    }
}