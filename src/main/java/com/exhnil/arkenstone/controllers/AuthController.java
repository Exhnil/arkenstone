package com.exhnil.arkenstone.controllers;

import com.exhnil.arkenstone.dto.UserDTO;
import com.exhnil.arkenstone.entities.UserEntity;
import com.exhnil.arkenstone.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public boolean login(@RequestBody UserDTO user){
        return authService.login(user);
    }
}
