package com.gladkiei.tasktracker.mapper;

import com.gladkiei.tasktracker.dtos.task.TaskRequestDto;
import com.gladkiei.tasktracker.dtos.task.TaskResponseDto;
import com.gladkiei.tasktracker.enums.TaskStatus;
import com.gladkiei.tasktracker.models.Task;
import com.gladkiei.tasktracker.models.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.sql.Timestamp;
import java.time.Instant;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "user")
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "completedAt", ignore = true)
    Task TaskRequestDtoToTask(TaskRequestDto taskRequestDto, User user);

    @AfterMapping
    default void setDefaults(@MappingTarget Task task) {
        task.setCreatedAt(Timestamp.from(Instant.now()));
        task.setStatus(TaskStatus.TODO);
    }

    @Mapping(target = "userId", source = "user.id")
    TaskResponseDto TaskToTaskResponseDto(Task task);

}
