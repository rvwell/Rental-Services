package com.infnet.user.controller;

import com.infnet.user.domain.model.User;
import com.infnet.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        return ResponseEntity.ok(userService.create(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Long id) {
        return userService.get(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<User>> list() {
        return ResponseEntity.ok(userService.list());
    }

    @GetMapping("/by-email")
    public ResponseEntity<Map<String, Object>> byEmail(@RequestParam String email) {
        return userService.findByEmail(email)
            .map(u -> {
                Map<String, Object> body = new java.util.HashMap<>();
                body.put("id", u.getId());
                body.put("email", u.getEmail());
                body.put("passwordHash", u.getPasswordHash());
                return ResponseEntity.ok(body);
            })
            .orElse(ResponseEntity.notFound().build());
    }
}