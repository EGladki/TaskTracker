package com.gladkiei.tasktracker.repositories;

import com.gladkiei.tasktracker.enums.TaskStatus;
import com.gladkiei.tasktracker.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByStatus(TaskStatus status);
}
