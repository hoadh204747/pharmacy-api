package com.howie.pharmacy.pharmacy_store.dto.order;

import java.math.BigDecimal;

import com.howie.pharmacy.pharmacy_store.dto.shippingaddress.ShippingAddressCreateDto;
import com.howie.pharmacy.pharmacy_store.entity.Order;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateDto {
    //private Integer userId;
    private Order.Payment paymentMethod;
    private BigDecimal totalPrice;
    private ShippingAddressCreateDto shippingAddress;
}
