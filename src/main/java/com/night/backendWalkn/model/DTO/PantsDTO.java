package com.night.backendWalkn.model.DTO;

import com.night.backendWalkn.model.enums.ClothingSize;
import com.night.backendWalkn.model.enums.OuterwearType;
import com.night.backendWalkn.model.enums.PantsType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class PantsDTO extends BaseProductDTO {

    @NotNull(message = "{dto.pants.size.required}")
    private ClothingSize size;

    @NotNull(message = "{dto.pants.type.required}")
    private PantsType type;

    @Override
    public Map<String, Object> getAttributes() {
        return Map.of("size", size, "type", type);
    }

    @Override
    public void setAttributes(Map<String, Object> attributes) {
        try {
            size = ClothingSize.valueOf(attributes.get("size").toString());
            type = PantsType.valueOf(attributes.get("type").toString());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }
}