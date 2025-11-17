package com.infnet.notification.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.function.Consumer;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class Consumers {
    private final StreamBridge streamBridge;
    @Bean
    public Consumer<Map<String, Object>> productEvents() {
        return event -> log.info("product event received: {}", event);
    }

    @Bean
    public Consumer<Map<String, Object>> rentalEvents() {
        return event -> {
            try {
                log.info("rental event received: {}", event);
                // process normally
            } catch (Exception ex) {
                log.error("processing error, sending to DLQ", ex);
                streamBridge.send("rentalEventsDlq-out-0", event);
            }
        };
    }
}