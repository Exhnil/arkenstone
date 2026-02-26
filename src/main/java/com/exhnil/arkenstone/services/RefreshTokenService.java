package com.exhnil.arkenstone.services;

import com.exhnil.arkenstone.entities.RefreshToken;
import com.exhnil.arkenstone.entities.UserEntity;
import com.exhnil.arkenstone.repositories.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private JwtService jwtService;

    public String createRefreshToken(UserEntity user) {
        String token = UUID.randomUUID().toString();
        refreshTokenRepository.save(new RefreshToken(user, token));
        return token;
    }

    public Optional<String> refresh(String refreshToken) {
        return refreshTokenRepository.findByToken(refreshToken).filter(t -> !t.isRevoked() && t.getExpires().isAfter(LocalDateTime.now()))
                .map(t -> jwtService.generateToken(t.getUser()));
    }
}
