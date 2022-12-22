package com.logger.Logger.mappers;

import com.logger.Logger.dtos.UserRegisterDto;
import com.logger.Logger.model.User;
import org.springframework.stereotype.Component;

@Component
public class RegisterMapper implements GenericMapper<User, UserRegisterDto> {

    public User toEntity(UserRegisterDto dto) {
        return new User(dto.getUsername(),dto.getPassword(), dto.getEmail());
    }

    public UserRegisterDto toDto(User entity) {
        return new UserRegisterDto(entity.getUsername(),entity.getPassword(), entity.getEmail());
    }
}
