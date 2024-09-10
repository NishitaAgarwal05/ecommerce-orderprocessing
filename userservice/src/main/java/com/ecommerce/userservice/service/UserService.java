package com.ecommerce.userservice.service;

import com.ecommerce.userservice.dto.UserDto;
import com.ecommerce.userservice.entities.UserEntity;
import com.ecommerce.userservice.mapper.UserMapper;
import com.ecommerce.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public UserDto registerUser(UserDto UserDto) {
        // Convert DTO to entity
        UserEntity user = userMapper.toUser(UserDto);

        // Encrypt password and add default role
//        user.setPassword(passwordEncoder.encode(UserDto.getUsername()));  // Dummy password logic for demo
        user.getRoles().add("ROLE_USER");

        // Save entity and convert back to DTO
        return userMapper.toUserDTO(userRepository.save(user));
    }

    public Optional<UserDto> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toUserDTO);
    }

    public List<UserDto> findAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserDTO)
                .collect(Collectors.toList());
    }

    public UserDto updateUserProfile(Long userId, UserDto updatedUserDto) {
        return userRepository.findById(userId).map(user -> {
            // Update fields
            user.setUsername(updatedUserDto.getUsername());
            user.setEmail(updatedUserDto.getEmail());

            // Save and return updated DTO
            return userMapper.toUserDTO(userRepository.save(user));
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
