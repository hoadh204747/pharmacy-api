package com.howie.pharmacy.pharmacy_store.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.howie.pharmacy.pharmacy_store.dto.brand.BrandDto;
import com.howie.pharmacy.pharmacy_store.dto.brand.BrandResponseDto;
import com.howie.pharmacy.pharmacy_store.dto.brand.BrandCreateDto;

@Component
public interface BrandService {
    List<BrandResponseDto> findAll();

    Optional<BrandResponseDto> create(BrandCreateDto brandCreateDto);

    Optional<BrandResponseDto> update(Integer id, BrandCreateDto brandCreateDto);

    void delete(Integer id);

    Optional<BrandDto> findById(Integer id);

    List<BrandResponseDto> getBrandsByCategory(Integer categoryId);
}
