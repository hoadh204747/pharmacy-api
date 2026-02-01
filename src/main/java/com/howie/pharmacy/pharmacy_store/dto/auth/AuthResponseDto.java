package com.howie.pharmacy.pharmacy_store.dto.auth;

import com.howie.pharmacy.pharmacy_store.dto.user.UserDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseDto {
    private String token;
    private UserDto user;
}
