package com.night.backendWalkn.model.entities;


import com.night.backendWalkn.model.enums.Season;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.util.Map;

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
    private Season season;

    @Type(value = JsonBinaryType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> attributes;
}
