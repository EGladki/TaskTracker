package com.gladkiei.tasktracker.services.event.publisher;

import com.gladkiei.tasktracker.dtos.user.UserResponseDto;

public interface EventPublisher {
    void sendRegistrationEvent(UserResponseDto userResponseDto);
}
