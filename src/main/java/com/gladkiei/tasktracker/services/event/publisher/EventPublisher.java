package com.gladkiei.tasktracker.services.event.publisher;

import com.gladkiei.tasktracker.dtos.event.RegistrationEventDto;
import com.gladkiei.tasktracker.dtos.task.TaskResponseDto;

public interface EventPublisher {
    void sendRegistrationEvent(RegistrationEventDto registrationEventDto);

    void sendNewTaskEvent(TaskResponseDto taskResponseDto);
}
