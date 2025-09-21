package com.loganalytics.backend.controller;

import com.loganalytics.backend.dto.AuthRequest;
import com.loganalytics.backend.dto.AuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Base64;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    // Simple GET for smoke test
    @GetMapping
    public ResponseEntity<Map<String, Object>> health() {
        log.info("GET /auth -> health check");
        return ResponseEntity.ok(Map.of(
                "service", "auth",
                "status", "ok",
                "timestamp", Instant.now().toString()));
    }

    // POST /auth/login -> accepts JSON body { username, password } and returns a
    // dummy token
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest req) {
        log.info("POST /auth/login -> username={}", req.getUsername());

        // dummy authentication logic (replace with real auth)
        boolean ok = "demo".equals(req.getUsername()) && "demo".equals(req.getPassword());

        if (!ok) {
            log.warn("Auth failed for username={}", req.getUsername());
            return ResponseEntity.status(401).body(new AuthResponse(null, "Invalid credentials"));
        }

        // create a simple base64 "token" (NOT a real JWT) as placeholder
        String tokenPayload = req.getUsername() + ":" + Instant.now().toString();
        String token = Base64.getEncoder().encodeToString(tokenPayload.getBytes());

        log.info("Auth succeeded for username={}, token={}", req.getUsername(), token);
        AuthResponse resp = new AuthResponse(token, "success");
        return ResponseEntity.ok(resp);
    }
}
