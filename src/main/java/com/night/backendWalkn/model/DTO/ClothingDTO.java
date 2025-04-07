package com.night.backendWalkn.model.DTO;

import com.night.backendWalkn.model.enums.ClothingSize;
import com.night.backendWalkn.model.enums.ClothingType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClothingDTO extends ProductDTO {
    @NotNull(message = "{dto.clothing.size.required}")
    private ClothingSize size;

    @NotNull(message = "{dto.clothing.type.required}")
    private ClothingType type;
}