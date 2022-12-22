package com.logger.Logger.mappers;

import com.logger.Logger.dtos.UserDto;
import com.logger.Logger.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements GenericMapper<User,UserDto>{
    public User toEntity(UserDto dto) {
        return new User(dto.getUsername(), dto.getEmail());
    }

    public UserDto toDto(User entity) {
        return new UserDto(entity.getUsername(), entity.getEmail());
    }
}
