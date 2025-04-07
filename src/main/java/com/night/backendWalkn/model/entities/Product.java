package com.night.backendWalkn.model.entities;


import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Map;

import org.hibernate.annotations.Type;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    private String brand;
    private String model;
    private String color;
    private int cost;

    @Type(value = JsonBinaryType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> attributes;
}
