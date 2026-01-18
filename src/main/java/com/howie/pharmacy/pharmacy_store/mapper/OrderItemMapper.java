package com.howie.pharmacy.pharmacy_store.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.howie.pharmacy.pharmacy_store.dto.order.OrderItemCreateDto;
import com.howie.pharmacy.pharmacy_store.dto.order.OrderItemDto;
import com.howie.pharmacy.pharmacy_store.entity.OrderItem;

// @Mapper(componentModel = "spring")
// public interface OrderItemMapper {
//     OrderItem toEntity(OrderItemCreateDto dto);

//     OrderItemDto toDto(OrderItem entity);

//     List<OrderItemDto> toDtoList(List<OrderItem> entities);
// }

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(target = "product", ignore = true) // Sẽ gán thủ công trong Service hoặc dùng ProductRepository
    @Mapping(target = "order", ignore = true)
    OrderItem toEntity(OrderItemCreateDto dto);

    // Mapping từ Entity sang DTO để trả về API
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName") // Nếu DTO có trường này
    OrderItemDto toDto(OrderItem entity);

    List<OrderItemDto> toDtoList(List<OrderItem> entities);
}