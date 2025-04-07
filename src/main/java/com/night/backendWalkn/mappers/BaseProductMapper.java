package com.night.backendWalkn.mappers;

import com.night.backendWalkn.model.DTO.BaseProductDTO;
import com.night.backendWalkn.model.entities.Product;

public class BaseProductMapper {

    public static Product toEntity(BaseProductDTO dto) {
        Product product = new Product();

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

        dto.setBrand(product.getBrand());
        dto.setModel(product.getModel());
        dto.setSeason(product.getSeason());
        dto.setCost(product.getCost());
        dto.setColor(product.getColor());

        dto.setAttributes(product.getAttributes());

        return dto;
    }
}
