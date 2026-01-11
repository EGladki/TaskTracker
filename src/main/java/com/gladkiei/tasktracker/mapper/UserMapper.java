package com.gladkiei.tasktracker.mapper;

import com.gladkiei.tasktracker.dtos.AuthRequestDto;
import com.gladkiei.tasktracker.dtos.UserResponseDto;
import com.gladkiei.tasktracker.models.User;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    User AuthRequestDtoToUser(AuthRequestDto authRequestDto);

    UserResponseDto userToUserResponseDto(User user);
}
