package com.howie.pharmacy.pharmacy_store.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.howie.pharmacy.pharmacy_store.dto.product.ProductCreateDto;
import com.howie.pharmacy.pharmacy_store.dto.product.ProductDto;
import com.howie.pharmacy.pharmacy_store.dto.product.ProductResponseDto;

@Component
public interface ProductService {
    List<ProductResponseDto> findAll();
    Optional<ProductResponseDto> create(ProductCreateDto productCreateDto);
    Optional<ProductResponseDto> update(Integer id, ProductCreateDto productCreateDto);
    void delete(Integer id);
    Optional<ProductDto> findById(Integer id);
}
