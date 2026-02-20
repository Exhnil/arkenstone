package com.exhnil.arkenstone.controllers;

import com.exhnil.arkenstone.dto.UserDTO;
import com.exhnil.arkenstone.entities.UserEntity;
import com.exhnil.arkenstone.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<?> getUser() {
        Map<String,String> response = new HashMap<>();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/user")
    public Optional<UserEntity> getUserByEmail(@RequestBody UserDTO user){
        Optional<UserEntity> userEntity= userService.findByEmail(user.getEmail());
        System.out.println(userEntity.isPresent());
        return userEntity;
    }

    @PostMapping("/register")
    public UserEntity saveUser(@RequestBody UserDTO user) {
        UserEntity userEntity =  new UserEntity(user.getEmail(),user.getPassword());
        return userService.saveUser(userEntity);
    }
}
