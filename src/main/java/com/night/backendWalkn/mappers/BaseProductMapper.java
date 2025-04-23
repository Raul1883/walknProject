package com.night.backendWalkn.mappers;

import com.night.backendWalkn.model.DTO.BaseProductDTO;
import com.night.backendWalkn.model.entities.Product;

import java.util.Objects;

public class BaseProductMapper {

    public static Product toEntity(BaseProductDTO dto) {
        Product product = new Product();

        Long id = product.getId();
        if (!Objects.isNull(id)) {
            product.setId(id);
        }

        product.setBrand(dto.getBrand());
        product.setModel(dto.getModel());
        product.setCost(dto.getCost());
        product.setColor(dto.getColor());
        product.setSeason(dto.getSeason());
        product.setAttributes(dto.getAttributes());

        product.setType(ProductDtoFactory.resolveType(dto));

        return product;
    }

    public static BaseProductDTO toDto(Product product) {
        BaseProductDTO dto = ProductDtoFactory.createDto(product.getType());

        dto.setId(product.getId());
        dto.setBrand(product.getBrand());
        dto.setModel(product.getModel());
        dto.setSeason(product.getSeason());
        dto.setCost(product.getCost());
        dto.setColor(product.getColor());

        dto.setAttributes(product.getAttributes());

        return dto;
    }
}
