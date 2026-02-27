package com.exhnil.arkenstone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RefreshTokenRequest {
    private String refreshToken;
}
