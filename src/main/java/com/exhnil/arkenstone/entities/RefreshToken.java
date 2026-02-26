package com.exhnil.arkenstone.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
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

    @ManyToOne
    private UserEntity user;

    private boolean revoked;


}
