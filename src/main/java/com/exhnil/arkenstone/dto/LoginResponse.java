package com.exhnil.arkenstone.dto;

import com.exhnil.arkenstone.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginResponse {
    private String token;
    private UserEntity user;
}
