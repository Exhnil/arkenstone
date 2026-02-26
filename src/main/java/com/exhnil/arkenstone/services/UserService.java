package com.exhnil.arkenstone.services;

import com.exhnil.arkenstone.entities.UserEntity;
import com.exhnil.arkenstone.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public UserEntity saveUser(String email, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        UserEntity userEntity = new UserEntity(email, encodedPassword);
        return userRepository.save(userEntity);
    }

    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
