package com.loganalytics.backend.controller;

import com.loganalytics.backend.dto.OrderRequest;
import com.loganalytics.backend.dto.OrderResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    // in-memory store for demo purposes
    private final Map<String, OrderResponse> store = Collections.synchronizedMap(new LinkedHashMap<>());

    // GET /order -> list orders (smoke + simple list)
    @GetMapping
    public ResponseEntity<List<OrderResponse>> listOrders() {
        List<OrderResponse> list = new ArrayList<>(store.values());
        log.info("GET /order -> {} orders", list.size());
        return ResponseEntity.ok(list);
    }

    // POST /order -> create an order (accepts JSON body)
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest req) {
        log.info("POST /order -> create order: {}", req);
        String id = "ORD-" + System.currentTimeMillis();
        OrderResponse resp = new OrderResponse(id, req.getProductId(), req.getQuantity(),
                req.getPricePerUnit(), "CREATED", Instant.now().toString());
        store.put(id, resp);
        log.info("Order created: {}", resp);
        return ResponseEntity.ok(resp);
    }

    // GET /order/{id} -> retrieve single order
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable String id) {
        OrderResponse resp = store.get(id);
        if (resp == null) {
            log.warn("GET /order/{} -> not found", id);
            return ResponseEntity.notFound().build();
        }
        log.info("GET /order/{} -> found", id);
        return ResponseEntity.ok(resp);
    }
}
