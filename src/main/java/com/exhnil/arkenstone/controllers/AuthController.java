package com.exhnil.arkenstone.controllers;

import com.exhnil.arkenstone.dto.LoginResponse;
import com.exhnil.arkenstone.dto.UserCredentials;
import com.exhnil.arkenstone.services.AuthService;
import com.exhnil.arkenstone.services.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserCredentials creds) {
        Optional<LoginResponse> loginResponse = authService.login(creds);

        if (loginResponse.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String refreshToken = refreshTokenService.createRefreshToken(loginResponse.get().getUser());

        return ResponseEntity.ok(Map.of("accessToken", loginResponse.get().getToken(), "refreshToken", refreshToken));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody String refreshToken) {
        return ResponseEntity.ok(refreshTokenService.refresh(refreshToken));
    }
}
