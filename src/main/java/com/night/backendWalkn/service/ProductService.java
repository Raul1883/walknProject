package com.night.backendWalkn.service;

import com.night.backendWalkn.mappers.BaseProductMapper;
import com.night.backendWalkn.model.DTO.BaseProductDTO;
import com.night.backendWalkn.model.DTO.ProductFilterRequest;
import com.night.backendWalkn.model.entities.Product;
import com.night.backendWalkn.repository.ProductRepository;
import com.night.backendWalkn.repository.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public void SaveProduct(Product product) {
        repository.save(product);
    }

    public void SaveProduct(BaseProductDTO dto) {
        Product product = BaseProductMapper.toEntity(dto);
        repository.save(product);
    }

    public List<BaseProductDTO> GetAllProductDto() {
        return repository.findAll()
                         .stream()
                         .map(BaseProductMapper::toDto)
                         .toList();
    }

    public BaseProductDTO getProductDto(Long id) {
        return BaseProductMapper.toDto(repository.findById(id)
                                                 .orElseThrow(() -> new NoSuchElementException(
                                                         "Dto with id %s does not exist".formatted(id.toString()))));
    }

    public List<BaseProductDTO> findByFilter(ProductFilterRequest filter) {
        Specification<Product> spec = Specification
                .where(ProductSpecification.hasBrand(filter.brand))
                .and(ProductSpecification.hasModel(filter.model))
                .and(ProductSpecification.hasColor(filter.color))
                .and(ProductSpecification.hasSeason(filter.season))
                .and(ProductSpecification.costBetween(filter.minCost, filter.maxCost));

        return repository.findAll(spec)
                         .stream()
                         .map(BaseProductMapper::toDto)
                         .toList();
    }
}

