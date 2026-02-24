package com.exhnil.arkenstone.controllers;

import com.exhnil.arkenstone.dto.UserCredentials;
import com.exhnil.arkenstone.entities.UserEntity;
import com.exhnil.arkenstone.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public String hello() {
        return "Hello World";
    }

    @PostMapping("/user")
    public Optional<UserEntity> getUserByEmail(@RequestBody UserCredentials request){
        Optional<UserEntity> userEntity= userService.findByEmail(request.getEmail());
        System.out.println(userEntity.isPresent());
        return userEntity;
    }

    @PostMapping("/register")
    public UserEntity saveUser(@RequestBody UserCredentials request) {
        return userService.saveUser(request.getEmail(), request.getPassword());
    }
}
