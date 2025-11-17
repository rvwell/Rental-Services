package com.infnet.rental.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

@Configuration
public class FeignAuthConfig {
    @Bean
    public RequestInterceptor jwtPropagationInterceptor() {
        return template -> {
            var auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth instanceof JwtAuthenticationToken token) {
                String value = token.getToken().getTokenValue();
                template.header("Authorization", "Bearer " + value);
            }
        };
    }
}