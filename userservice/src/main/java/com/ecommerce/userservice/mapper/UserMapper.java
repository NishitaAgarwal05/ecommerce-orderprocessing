package com.ecommerce.userservice.mapper;

import com.ecommerce.userservice.dto.UserDto;
import com.ecommerce.userservice.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    // Convert User entity to UserDTO
    @Mapping(target = "roles", source = "roles")
    UserDto toUserDTO(UserEntity user);

    // Convert UserDTO to User entity
    @Mapping(target = "password", ignore = true)
    UserEntity toUser(UserDto userDTO);
}
