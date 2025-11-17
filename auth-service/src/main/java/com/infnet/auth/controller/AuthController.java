package com.infnet.auth.controller;

import com.infnet.auth.feign.UserClient;
import com.infnet.auth.service.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserClient userClient;
    private final JwtTokenService jwtTokenService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");
        Map<String, Object> user = userClient.findByEmail(email);
        if (user == null || !passwordEncoder.matches(password, (String) user.get("passwordHash"))) {
            return ResponseEntity.status(401).body(Map.of("error", "invalid_credentials"));
        }
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        claims.put("userId", String.valueOf(user.get("id")));
        String token = jwtTokenService.generateToken(String.valueOf(user.get("id")), claims);
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/dev-login")
    public ResponseEntity<Map<String, Object>> devLogin(@RequestBody Map<String, String> body) {
        String userId = body.get("userId");
        String email = body.get("email");
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        claims.put("userId", userId);
        String token = jwtTokenService.generateToken(userId, claims);
        return ResponseEntity.ok(Map.of("token", token));
    }
}
