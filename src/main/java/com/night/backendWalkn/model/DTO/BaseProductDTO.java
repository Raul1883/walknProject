package com.night.backendWalkn.model.DTO;

import com.night.backendWalkn.model.enums.Season;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseProductDTO {
    private Long id;

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


    // возможно здесь можно сделать общую логику, но я уверен что будет расширение.
    public abstract Map<String, Object> getAttributes();

    public abstract void setAttributes(Map<String, Object> attributes);
}
