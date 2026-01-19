package com.gladkiei.tasktracker.controllers;

import com.gladkiei.tasktracker.dtos.task.TaskRequestDto;
import com.gladkiei.tasktracker.dtos.task.TaskResponseDto;
import com.gladkiei.tasktracker.dtos.task.TaskUpdateDto;
import com.gladkiei.tasktracker.security.UserDetailsImpl;
import com.gladkiei.tasktracker.services.TaskService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public TaskResponseDto add(@RequestBody TaskRequestDto taskRequestDto,
                               @AuthenticationPrincipal UserDetailsImpl principal) {
        return taskService.add(taskRequestDto, principal.getUserId());
    }

    @GetMapping
    public List<TaskResponseDto> getTasks(@AuthenticationPrincipal UserDetailsImpl principal) {
        return taskService.get(principal.getUserId());
    }

    @GetMapping("/{taskId}")
    public TaskResponseDto getTask(@PathVariable Long taskId,
                                   @AuthenticationPrincipal UserDetailsImpl principal) {
        return taskService.get(taskId, principal.getUserId());
    }

    @PatchMapping
    public TaskResponseDto update(@RequestBody TaskUpdateDto taskUpdateDto,
                                  @AuthenticationPrincipal UserDetailsImpl principal) {
        return taskService.update(taskUpdateDto, principal.getUserId());
    }

    @DeleteMapping("/{taskId}")
    public void delete(@PathVariable Long taskId,
                       @AuthenticationPrincipal UserDetailsImpl principal) {
        taskService.delete(taskId, principal.getUserId());
    }
}
