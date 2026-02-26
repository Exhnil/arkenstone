package com.exhnil.arkenstone.services;

import com.exhnil.arkenstone.dto.LoginResponse;
import com.exhnil.arkenstone.dto.UserCredentials;
import com.exhnil.arkenstone.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public Optional<LoginResponse> login(UserCredentials request) {
        return userRepository.findByEmail(request.getEmail())
                .filter(u -> passwordEncoder.matches(request.getPassword(), u.getPassword())).map(u -> {
                    String jwt = jwtService.generateToken(u);
                    return new LoginResponse(jwt, u);
                });
    }
}
