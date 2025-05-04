package com.night.backendWalkn.events.spring;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class OrderCreatedEvent {
    private final Long id;
}
