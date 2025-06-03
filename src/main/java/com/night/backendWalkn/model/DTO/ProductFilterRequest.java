package com.night.backendWalkn.model.DTO;

import com.night.backendWalkn.model.enums.Season;

public class ProductFilterRequest {
    public String brand;
    public String model;
    public String color;
    public Season season;
    public Integer minCost;
    public Integer maxCost;
}