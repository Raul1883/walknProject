package com.night.backendWalkn.repository;

import com.night.backendWalkn.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {


}
