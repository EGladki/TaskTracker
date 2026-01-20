package com.gladkiei.tasktracker.dtos.task;

import com.gladkiei.tasktracker.enums.TaskStatus;

import java.sql.Timestamp;

public record TaskResponseDto(
        Long id,
        String title,
        String description,
        String createdAt,
        Timestamp completionTime,
        TaskStatus status,
        Long userId
) {

}
