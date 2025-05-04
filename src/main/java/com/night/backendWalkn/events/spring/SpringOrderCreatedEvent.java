package com.night.backendWalkn.events.spring;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SpringOrderCreatedEvent {
    private final Long id;
}
