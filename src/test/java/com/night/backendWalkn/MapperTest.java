package com.night.backendWalkn;


import com.night.backendWalkn.mappers.BaseProductMapper;
import com.night.backendWalkn.model.DTO.ShoeDTO;
import com.night.backendWalkn.model.entities.Product;
import com.night.backendWalkn.model.enums.ProductType;
import com.night.backendWalkn.model.enums.Season;
import com.night.backendWalkn.model.enums.ShoeType;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class MapperTest {
    private Validator validator;

    private final ProductType type = ProductType.SHOE;
    private final String brand = "Nike";
    private final String model = "Air";
    private final String color = "White";
    private final int cost = 12000;
    private final Season season = Season.ALL_SEASON;
    private final ShoeType shoeType = ShoeType.BOOTS;
    private final int shoeSize = 42;

    private Product shoe;
    private ShoeDTO shoeDto;

    @BeforeEach
    void setUp() {
        Map<String, Object> attributes = Map.of("type", shoeType, "size", shoeSize);
        shoe = new Product(0l, type, brand, model, color, cost, season, attributes);

        shoeDto = new ShoeDTO();
        shoeDto.setCost(cost);
        shoeDto.setType(shoeType);
        shoeDto.setBrand(brand);
        shoeDto.setModel(model);
        shoeDto.setColor(color);
        shoeDto.setSize(shoeSize);
        shoeDto.setSeason(season);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testDtoToEntity() {
        var entity = BaseProductMapper.toEntity(shoeDto);
        assertEquals(shoe, entity);
    }

    @Test
    void testEntityToDto() {
        var dto = BaseProductMapper.toDto(shoe);
        assertEquals(shoeDto, dto);
    }

    @Test
    void testBrokenDtoToEntity() {
        shoeDto.setModel(null);
        shoeDto.setCost(-5);
        Set<ConstraintViolation<ShoeDTO>> violations = validator.validate(shoeDto);
        assertFalse(violations.isEmpty());
    }




}
