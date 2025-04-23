package com.night.backendWalkn.model.DTO;

import com.night.backendWalkn.model.enums.ShoeType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor

public class ShoeDTO extends BaseProductDTO {

    @Min(value = 35, message = "{dto.shoe.size.min}")
    @Max(value = 50, message = "{dto.shoe.size.min}")
    private int size;

    @NotNull(message = "{dto.shoe.type.required}")
    private ShoeType type;

    @Override
    public Map<String, Object> getAttributes() {
        return Map.of("size", size, "type", type);
    }

    @Override
    public void setAttributes(Map<String, Object> attributes) {
        try {
            size = Integer.parseInt(attributes
                    .get("size")
                    .toString());
            type = ShoeType.valueOf(attributes
                    .get("type")
                    .toString());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }
}
