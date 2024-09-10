package com.ecommerce.userservice.controller;

import com.ecommerce.userservice.dto.UserDto;
import com.ecommerce.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto user) {
        UserDto registeredUser = userService.registerUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        return userService.findByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUserProfile(@PathVariable Long id, @RequestBody UserDto user) {
        UserDto updatedUser = userService.updateUserProfile(id, user);
        return ResponseEntity.ok(updatedUser);
    }
}
