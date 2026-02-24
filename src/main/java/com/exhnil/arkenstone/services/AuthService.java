package com.exhnil.arkenstone.services;

import com.exhnil.arkenstone.dto.UserCredentials;
import com.exhnil.arkenstone.dto.UserDTO;
import com.exhnil.arkenstone.entities.UserEntity;
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

    public Optional<UserDTO> login(UserCredentials request) {
        Optional<UserEntity> user = userRepository.findByEmail(request.getEmail());
        if(user.isEmpty()) return Optional.empty();
        if(!passwordEncoder.matches(request.getPassword(),user.get().getPassword())) return Optional.empty();
        return Optional.of(new UserDTO(user.get().getId(), user.get().getEmail()));
    }
}
