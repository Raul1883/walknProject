package com.night.backendWalkn.model.entities;


import com.night.backendWalkn.model.enums.OrderStatus;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class CustomerOrder {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Exclude
    private Long id;

    private OrderStatus status;

    @ElementCollection
    private Set<Long> productIdSet;

    private OffsetDateTime createdAt;

    private String clientName;
    private String deliveryAddress;
    private String contactPhone;
    private String telegramId;
    private String email;
}
