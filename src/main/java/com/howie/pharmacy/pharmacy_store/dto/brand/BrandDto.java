package com.howie.pharmacy.pharmacy_store.dto.brand;

import java.time.LocalDateTime;
import java.util.List;

import com.howie.pharmacy.pharmacy_store.dto.category.CategoryResponseDto;
import com.howie.pharmacy.pharmacy_store.dto.product.ProductResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrandDto {
    private Integer id;
    private String name;
    private String slug;
    private CategoryResponseDto category;
    private List<ProductResponseDto> products;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
