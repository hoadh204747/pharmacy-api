package com.howie.pharmacy.pharmacy_store.mapper;

import com.howie.pharmacy.pharmacy_store.dto.user.UserCreateDto;
import com.howie.pharmacy.pharmacy_store.dto.user.UserDto;
import com.howie.pharmacy.pharmacy_store.dto.user.UserSummaryDto;
import com.howie.pharmacy.pharmacy_store.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Chuyển từ Entity sang DTO trả về
    UserDto toDto(User user);

    // Chuyển từ Entity sang bản tóm tắt (cho Order)
    UserSummaryDto toSummaryDto(User user);

    List<UserDto> toDtoList(List<User> users);

    // Chuyển từ CreateDto sang Entity để lưu vào DB
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "role", constant = "CUSTOMER")
    User toEntity(UserCreateDto dto);

    // Cập nhật thông tin User
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "orders", ignore = true)
    void updateEntityFromDto(UserDto dto, @MappingTarget User user);
}