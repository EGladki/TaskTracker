package com.gladkiei.tasktracker.dtos.report;

import com.gladkiei.tasktracker.enums.TaskStatus;

public record EmailTaskTitleStatusReport(
        String email,
        String title,
        TaskStatus status
) {
}
