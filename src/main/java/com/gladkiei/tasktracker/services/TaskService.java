package com.gladkiei.tasktracker.services;

import com.gladkiei.tasktracker.dtos.task.TaskRequestDto;
import com.gladkiei.tasktracker.dtos.task.TaskResponseDto;
import com.gladkiei.tasktracker.dtos.task.TaskUpdateDto;
import com.gladkiei.tasktracker.exceptions.NotFoundException;
import com.gladkiei.tasktracker.mapper.TaskMapper;
import com.gladkiei.tasktracker.models.Task;
import com.gladkiei.tasktracker.models.User;
import com.gladkiei.tasktracker.repositories.TaskRepository;
import com.gladkiei.tasktracker.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;

    @Transactional
    public TaskResponseDto add(TaskRequestDto taskRequestDto, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            Task task = taskMapper.TaskRequestDtoToTask(taskRequestDto, userOptional.get());
            Task saved = taskRepository.save(task);

            return taskMapper.TaskToTaskResponseDto(saved);
        } else {
            throw new NotFoundException("User with such id doesn't exists");
        }
    }

    @Transactional
    public List<TaskResponseDto> get(Long userId) {
        List<Task> tasks = taskRepository.findByUserId(userId);

        return tasks.stream().map(taskMapper::TaskToTaskResponseDto).toList();
    }

    @Transactional
    public TaskResponseDto get(Long taskId, Long userId) {
        Optional<Task> taskOptional = taskRepository.findByIdAndUserId(taskId, userId);

        if (taskOptional.isPresent()) {
            return taskMapper.TaskToTaskResponseDto(taskOptional.get());
        } else {
            throw new NotFoundException("Task not found for such user");
        }

    }

    @Transactional
    public void delete(Long taskId, Long userId) {
        Optional<Task> taskOptional = taskRepository.findByIdAndUserId(taskId, userId);

        if (taskOptional.isPresent()) {
            taskRepository.delete(taskOptional.get());
        } else {
            throw new NotFoundException("Task not found for such user");
        }
    }

    @Transactional
    public TaskResponseDto update(TaskUpdateDto taskUpdateDto, Long userId) {
        Optional<Task> taskOptional = taskRepository.findByIdAndUserId(taskUpdateDto.id(), userId);

        if (taskOptional.isEmpty()) {
            throw new NotFoundException("Task not found for such user");
        }

        Task task = taskOptional.get();

        if (taskUpdateDto.title() != null) {
            task.setTitle(taskUpdateDto.title());
        }

        if (taskUpdateDto.description() != null) {
            task.setDescription(taskUpdateDto.description());
        }

        if (taskUpdateDto.status() != null) {
            task.setStatus(taskUpdateDto.status());
        }

        return taskMapper.TaskToTaskResponseDto(task);

    }
}
