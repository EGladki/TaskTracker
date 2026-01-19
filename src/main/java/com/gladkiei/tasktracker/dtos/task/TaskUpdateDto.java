package com.gladkiei.tasktracker.dtos.task;

import com.gladkiei.tasktracker.enums.TaskStatus;
import jakarta.annotation.Nullable;

import java.sql.Timestamp;

public record TaskUpdateDto
        (Long id,
         @Nullable String title,
         @Nullable String description,
         @Nullable Timestamp completionTime,
         @Nullable TaskStatus status) {
}
