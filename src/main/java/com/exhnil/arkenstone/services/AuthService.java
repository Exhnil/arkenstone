package com.exhnil.arkenstone.services;

import com.exhnil.arkenstone.dto.LoginResponse;
import com.exhnil.arkenstone.dto.UserCredentials;
import com.exhnil.arkenstone.entities.RefreshToken;
import com.exhnil.arkenstone.entities.UserEntity;
import com.exhnil.arkenstone.repositories.RefreshTokenRepository;
import com.exhnil.arkenstone.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    public Optional<LoginResponse> login(UserCredentials request) {
        return userRepository.findByEmail(request.getEmail())
                .filter(u -> passwordEncoder.matches(request.getPassword(), u.getPassword()))
                .map(u -> {
                    String jwt = jwtService.generateToken(u);
                    String refreshToken = createRefreshToken(u);
                    return new LoginResponse(jwt, refreshToken);
                });
    }

    public void logout(String refreshToken) {
        refreshTokenRepository.findByToken(refreshToken).ifPresent(token -> {
            token.setRevoked(true);
            refreshTokenRepository.save(token);
        });
    }

    public String createRefreshToken(UserEntity user) {
        String token = UUID.randomUUID().toString();
        refreshTokenRepository.save(new RefreshToken(user, token));
        return token;
    }

    public Optional<String> refresh(String refreshToken) {
        return refreshTokenRepository.findByToken(refreshToken)
                .filter(t -> !t.isRevoked() && t.getExpires().isAfter(LocalDateTime.now()))
                .map(t -> jwtService.generateToken(t.getUser()));
    }

    @Scheduled(cron = "0 0 3 * * ?")
    public void cleanExpiredTokens() {
        refreshTokenRepository.deleteByExpiresBefore(LocalDateTime.now());
    }
}
