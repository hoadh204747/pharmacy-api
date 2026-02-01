package com.howie.pharmacy.pharmacy_store.dto.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDto {
    private String email;
    private String password;
    private String phone;
}