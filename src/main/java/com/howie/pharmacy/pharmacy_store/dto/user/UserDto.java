package com.howie.pharmacy.pharmacy_store.dto.user;

import com.howie.pharmacy.pharmacy_store.entity.User.ERole;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer id;
    private String username;
    private String email;
    private String phone;
    private String avatarUrl;
    private ERole role;
    private LocalDateTime createdAt;
}