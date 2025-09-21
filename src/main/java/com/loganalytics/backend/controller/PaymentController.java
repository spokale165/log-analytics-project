package com.loganalytics.backend.controller;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    // Simple GET for quick smoke-tests: GET /payment
    @GetMapping
    public ResponseEntity<Map<String, Object>> getPayment() {
        Map<String, Object> resp = new HashMap<>();
        resp.put("status", "ok");
        resp.put("service", "payment");
        resp.put("timestamp", Instant.now().toString());
        log.info("GET /payment -> {}", resp);
        return ResponseEntity.ok(resp);
    }

    // POST for creating a payment: POST /payment?amount=100
    @PostMapping
    public ResponseEntity<Map<String, Object>> makePayment(@RequestParam double amount) {
        log.info("Received payment request: amount={}", amount);

        // minimal dummy processing
        Map<String, Object> result = new HashMap<>();
        result.put("status", "processed");
        result.put("amount", amount);
        result.put("currency", "INR");
        result.put("processedAt", Instant.now().toString());
        result.put("reference", "PAY-" + System.currentTimeMillis());

        // log structured-ish info
        log.info("Payment processed: {}", result);
        return ResponseEntity.ok(result);
    }
}
