package com.howie.pharmacy.pharmacy_store.dto.brand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrandCreateDto {
    private String slug;
    private String name;
    private Integer categoryId;
}
