package com.night.backendWalkn.model.DTO;

import com.night.backendWalkn.model.enums.ClothingSize;
import com.night.backendWalkn.model.enums.OuterwearType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OuterwearDTO {

    @NotNull(message = "{dto.outerwear.size.required}")
    private ClothingSize size;

    @NotNull(message = "{dto.outerwear.type.required}")
    private OuterwearType type;
}
