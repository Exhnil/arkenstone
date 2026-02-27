package com.exhnil.arkenstone.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserCredentials {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
