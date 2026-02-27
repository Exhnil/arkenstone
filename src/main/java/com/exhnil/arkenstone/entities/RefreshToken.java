package com.exhnil.arkenstone.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
public class RefreshToken {

    public RefreshToken(UserEntity user, String token) {
        this.user = user;
        this.token = token;
        this.expires = LocalDateTime.now().plusDays(7);
        this.revoked = false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String token;

    private LocalDateTime expires;

    private boolean revoked;

    @ManyToOne
    private UserEntity user;

}
