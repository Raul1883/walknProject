package com.night.backendWalkn.model.DTO;

import com.night.backendWalkn.model.enums.ClothingSize;
import com.night.backendWalkn.model.enums.ClothingType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClothingDTO extends BaseProductDTO {
    @NotNull(message = "{dto.clothing.size.required}")
    private ClothingSize size;

    @NotNull(message = "{dto.clothing.type.required}")
    private ClothingType type;

    @Override
    public Map<String, Object> getAttributes() {
        return Map.of("size", size, "type", type);
    }

    @Override
    public void setAttributes(Map<String, Object> attributes) {
        try {
            size = ClothingSize.valueOf(attributes.get("size").toString());
            type = ClothingType.valueOf(attributes.get("type").toString());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }
}