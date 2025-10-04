package com.howie.pharmacy.pharmacy_store.dto.product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.howie.pharmacy.pharmacy_store.dto.brand.BrandResponseDto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Integer id;
    private String slug;
    private String name;
    private String description;
    private List<String> imageUrl;
    private BigDecimal price;
    private Float discount;
    private Integer amount;
    private Integer sold;
    private Boolean sale;
    private BrandResponseDto brand;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
