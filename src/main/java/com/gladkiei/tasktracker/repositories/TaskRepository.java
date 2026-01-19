package com.gladkiei.tasktracker.repositories;

import com.gladkiei.tasktracker.enums.TaskStatus;
import com.gladkiei.tasktracker.models.Task;
import com.gladkiei.tasktracker.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByStatus(TaskStatus status);

    List<Task> findByUserId(Long userId);

    Optional<Task> findByIdAndUserId(Long taskId, Long userId);
}
