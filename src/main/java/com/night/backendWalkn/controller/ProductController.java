package com.night.backendWalkn.controller;


import com.night.backendWalkn.service.ProductService;
import com.night.backendWalkn.model.DTO.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping("/products")
    public ResponseEntity<List<BaseProductDTO>> getAllProducts() {
        return ResponseEntity.ok(service.GetAllProductDto());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<BaseProductDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getProductDto(id));
    }

    @PostMapping("/create/shoe")
    public ResponseEntity<BaseProductDTO> createShoe(@RequestBody @Valid ShoeDTO dto) {
        service.SaveProduct(dto);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/create/clothing")
    public ResponseEntity<BaseProductDTO> createClothing(@RequestBody @Valid ClothingDTO dto) {
        service.SaveProduct(dto);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/create/outerwear")
    public ResponseEntity<BaseProductDTO> createOuterwear(@RequestBody @Valid OuterwearDTO dto) {
        service.SaveProduct(dto);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/create/pants")
    public ResponseEntity<BaseProductDTO> createPants(@RequestBody @Valid PantsDTO dto) {
        service.SaveProduct(dto);
        return ResponseEntity.ok(dto);
    }
}
