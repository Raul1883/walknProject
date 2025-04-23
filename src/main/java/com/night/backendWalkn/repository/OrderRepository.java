package com.night.backendWalkn.repository;

import com.night.backendWalkn.model.entities.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<CustomerOrder, Long> {
}
