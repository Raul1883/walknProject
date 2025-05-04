package com.night.backendWalkn.events.spring;


import com.night.backendWalkn.events.kafka.KafkaOrderCreatedEvent;
import com.night.backendWalkn.events.kafka.OrderEventProducer;
import com.night.backendWalkn.service.OrderService;
import com.night.backendWalkn.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventListener {

    private final OrderEventProducer producer;
    private final ProductService productService;
    private final OrderService orderService;

    @EventListener
    public void handleOrderCreated(SpringOrderCreatedEvent event) {
        producer.sendOrderCreated(KafkaOrderCreatedEvent.fromOrder(
                orderService.getOrderByID(event.getId()), productService));
    }
}
