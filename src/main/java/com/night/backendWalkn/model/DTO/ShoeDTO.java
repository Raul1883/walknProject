package com.night.backendWalkn.model.DTO;

import com.night.backendWalkn.model.enums.ShoeType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ShoeDTO {

    @Min(value = 35, message = "{dto.shoe.size.min}")
    @Max(value = 50, message = "{dto.shoe.size.min}")
    private int size;

    @NotNull(message = "{dto.shoe.type.required}")
    private ShoeType type;
}
