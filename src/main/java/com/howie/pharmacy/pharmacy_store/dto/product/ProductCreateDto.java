package com.howie.pharmacy.pharmacy_store.dto.product;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateDto {
    private String slug;
    private String name;
    private String description;
    private BigDecimal price;
    private Float discount;
    private Integer amount;
    private Integer brandId;
    private List<String> imageUrl;
}
