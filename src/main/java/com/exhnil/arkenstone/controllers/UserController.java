package com.exhnil.arkenstone.controllers;

import com.exhnil.arkenstone.dto.UserCredentials;
import com.exhnil.arkenstone.entities.UserEntity;
import com.exhnil.arkenstone.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/user")
    public ResponseEntity<String> getUserByEmail(Authentication authentication) {
        return ResponseEntity.ok(authentication.getName());
    }

    @PostMapping("/register")
    public UserEntity saveUser(@RequestBody UserCredentials request) {
        return userService.saveUser(request.getEmail(), request.getPassword());
    }
}
