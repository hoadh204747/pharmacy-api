package com.howie.pharmacy.pharmacy_store.dto.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSummaryDto {
    private Integer id;
    private String username;
    private String phone;
}