package com.night.backendWalkn.mappers;

import com.night.backendWalkn.model.DTO.*;
import com.night.backendWalkn.model.enums.ProductType;

import java.util.Map;
import java.util.function.Supplier;

public class ProductDtoFactory {

    private static final Map<ProductType, Supplier<BaseProductDTO>> typeToDtoMap = Map.of(
            ProductType.SHOE, ShoeDTO::new,
            ProductType.PANTS, PantsDTO::new,
            ProductType.CLOTHING, ClothingDTO::new,
            ProductType.OUTERWEAR, OuterwearDTO::new
                                                                                         );

    private static final Map<Class<? extends BaseProductDTO>, ProductType> dtoClassToTypeMap = Map.of(
            ShoeDTO.class, ProductType.SHOE,
            PantsDTO.class, ProductType.PANTS,
            ClothingDTO.class, ProductType.CLOTHING,
            OuterwearDTO.class, ProductType.OUTERWEAR
                                                                                                     );

    public static BaseProductDTO createDto(ProductType type) {
        Supplier<BaseProductDTO> supplier = typeToDtoMap.get(type);
        if (supplier == null)
            throw new IllegalArgumentException("Unsupported ProductType: " + type);
        return supplier.get();
    }

    public static ProductType resolveType(BaseProductDTO dto) {
        ProductType type = dtoClassToTypeMap.get(dto.getClass());
        if (type == null)
            throw new IllegalArgumentException("Unknown DTO class: " + dto.getClass()
                                                                          .getSimpleName());
        return type;
    }
}