package com.night.backendWalkn.events.kafka;

import com.night.backendWalkn.model.DTO.BaseProductDTO;
import com.night.backendWalkn.model.entities.CustomerOrder;
import com.night.backendWalkn.model.enums.OrderStatus;
import com.night.backendWalkn.service.ProductService;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;


@Data
@RequiredArgsConstructor
public class KafkaOrderCreatedEvent {
    private final Long id;
    private final OrderStatus status;
    private final OffsetDateTime createdAt;
    private final String clientName;
    private final String deliveryAddress;
    private final String contactPhone;
    private final String telegramId;
    private final String email;

    private final List<BaseProductDTO> productList;

    public static KafkaOrderCreatedEvent fromOrder(CustomerOrder order, ProductService service) {
        return new KafkaOrderCreatedEvent(
                order.getId(),
                order.getStatus(),
                order.getCreatedAt(),
                order.getClientName(),
                order.getDeliveryAddress(),
                order.getContactPhone(),
                order.getTelegramId(),
                order.getEmail(),
                order.getProductIdSet().stream().map(service::getProductDto).toList()
        );
    }
}
