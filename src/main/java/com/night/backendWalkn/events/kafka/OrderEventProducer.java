package com.night.backendWalkn.events.kafka;


import lombok.RequiredArgsConstructor;
import org.apache.kafka.shaded.com.google.protobuf.AbstractMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final static String TOPIC = "order.created";

    public void sendOrderCreated(OrderCreatedEvent event) {
        kafkaTemplate.send(TOPIC, event);
    }
}
