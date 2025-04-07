package com.night.backendWalkn.model.DTO;

import com.night.backendWalkn.model.enums.ClothingSize;
import com.night.backendWalkn.model.enums.PantsType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PantsDTO extends ProductDTO{

    @NotNull(message = "{dto.pants.size.required}")
    private ClothingSize size;

    @NotNull(message = "{dto.pants.type.required}")
    private PantsType type;
}