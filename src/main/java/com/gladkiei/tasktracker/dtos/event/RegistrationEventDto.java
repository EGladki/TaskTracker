package com.gladkiei.tasktracker.dtos.event;

public record RegistrationEventDto(
        String email,
        String title,
        String message
) {
}
