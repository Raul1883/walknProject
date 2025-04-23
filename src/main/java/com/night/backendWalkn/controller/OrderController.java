package com.night.backendWalkn.controller;

import com.night.backendWalkn.Service.OrderService;
import com.night.backendWalkn.model.entities.CustomerOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;

    @GetMapping("/orders")
    public ResponseEntity<List<CustomerOrder>> getAllOrders() {
        return ResponseEntity.ok(service.getOrders());
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<CustomerOrder> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(service.getOrderByID(id));
    }

    @PostMapping("/orders/create")
    public ResponseEntity<CustomerOrder> createOrder(@RequestBody CustomerOrder order) {
        return ResponseEntity.ok(service.createNewOrder(order));
    }

    @PostMapping("orders/close/{id}")
    public ResponseEntity<CustomerOrder> closeOrder(@PathVariable Long id) {
        return ResponseEntity.ok(service.closeOrder(id));
    }
}
