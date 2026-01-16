package com.gladkiei.tasktracker.mapper;

import com.gladkiei.tasktracker.dtos.AuthRequestDto;
import com.gladkiei.tasktracker.dtos.UserResponseDto;
import com.gladkiei.tasktracker.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User AuthRequestDtoToUser(AuthRequestDto authRequestDto);

    UserResponseDto userToUserResponseDto(User user);
}
