package com.night.backendWalkn.model.DTO;
import com.night.backendWalkn.model.enums.Season;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public abstract class ProductDTO {
    @NotBlank(message = "{dto.product.brand.required}")
    private String brand;

    @NotBlank(message = "{dto.product.model.required}")
    private String model;

    @NotBlank(message = "{dto.product.color.required}")
    private String color;

    @Positive(message = "{dto.product.cost.positive}")
    private int cost;

    @NotNull(message = "{dto.product.season.required}")
    private Season season;
}
