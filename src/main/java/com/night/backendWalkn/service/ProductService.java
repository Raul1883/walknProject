package com.night.backendWalkn.service;

import com.night.backendWalkn.mappers.BaseProductMapper;
import com.night.backendWalkn.model.DTO.BaseProductDTO;
import com.night.backendWalkn.model.entities.Product;
import com.night.backendWalkn.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
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
        return repository
                .findAll()
                .stream()
                .map(BaseProductMapper::toDto)
                .toList();
    }

    public BaseProductDTO getProductDto(Long id) {
        return BaseProductMapper.toDto(repository
                .findById(id)
                .orElseThrow(() -> (new NoSuchElementException(
                        "Dto with id %s does not exist".formatted(id.toString())))));
    }


}
