package com.howie.pharmacy.pharmacy_store.dto.order;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    private Integer id;
    private Integer productId;
    private String productName;
    private BigDecimal price;
    private Integer amount;
}
