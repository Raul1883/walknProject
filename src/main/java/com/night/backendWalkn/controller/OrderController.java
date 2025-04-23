package com.night.backendWalkn.controller;

import com.night.backendWalkn.service.OrderService;
import com.night.backendWalkn.model.entities.CustomerOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
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
