package com.night.backendWalkn.model.DTO;

import com.night.backendWalkn.model.enums.ClothingSize;
import com.night.backendWalkn.model.enums.OuterwearType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class OuterwearDTO extends ProductDTO{

    @NotNull(message = "{dto.outerwear.size.required}")
    private ClothingSize size;

    @NotNull(message = "{dto.outerwear.type.required}")
    private OuterwearType type;
}
