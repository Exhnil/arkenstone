package com.exhnil.arkenstone.repositories;

import com.exhnil.arkenstone.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByExpiresBefore(LocalDateTime date);
}
