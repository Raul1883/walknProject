package com.night.backendWalkn.events.spring;


import com.night.backendWalkn.events.kafka.OrderEventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventListener {

    private final OrderEventProducer producer;

    @EventListener
    public void handleOrderCreated(OrderCreatedEvent event) {
        producer.sendOrderCreated(
                new com.night.backendWalkn.events.kafka.OrderCreatedEvent(event.getId()));
    }
}
