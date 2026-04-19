package com.howie.pharmacy.pharmacy_store.dto.auth;

import lombok.Data;

@Data
public class RefreshTokenRequest {
    private String refreshToken;
}
