package com.exhnil.arkenstone.services;

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

    public Optional<UserEntity> login(UserDTO request) {
        Optional<UserEntity> user = userRepository.findByEmail(request.getEmail());
        if(user.isEmpty()) return Optional.empty();
        if(!user.get().getPassword().equals(request.getPassword())) return Optional.empty();
        return user;
    }
}
