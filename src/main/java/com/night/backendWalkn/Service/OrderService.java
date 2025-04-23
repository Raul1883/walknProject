package com.night.backendWalkn.Service;

import com.night.backendWalkn.model.entities.CustomerOrder;
import com.night.backendWalkn.model.enums.OrderStatus;
import com.night.backendWalkn.repository.OrderRepository;
import com.night.backendWalkn.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public List<CustomerOrder> getOrders() {
        return orderRepository.findAll();
    }

    public CustomerOrder getOrderByID(Long id) {
        return orderRepository
                .findById(id)
                .orElseThrow();
    }

    public CustomerOrder createNewOrder(CustomerOrder order) {
        order.setCreatedAt(OffsetDateTime.now());
        orderRepository.save(order);
        return order;
    }

    public CustomerOrder closeOrder(Long orderId) {
        CustomerOrder order = orderRepository
                .findById(orderId)
                .orElseThrow(() -> new NoSuchElementException(
                        "Dto with id %s does not exist".formatted(orderId.toString())));
        order.setStatus(OrderStatus.CLOSED);
        return order;
    }


}
