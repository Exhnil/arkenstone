package com.exhnil.arkenstone.services;

import com.exhnil.arkenstone.dto.LoginRequest;
import com.exhnil.arkenstone.dto.UserDTO;
import com.exhnil.arkenstone.entities.UserEntity;
import com.exhnil.arkenstone.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public boolean login(UserDTO request) {
        Optional<UserEntity> user = userRepository.findByEmail(request.getEmail());
        return user.isPresent();
    }
}
