package com.howie.pharmacy.pharmacy_store.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemCreateDto {
    private Integer productId;
    private Integer amount;
}