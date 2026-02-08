package com.gladkiei.tasktracker.dtos.report;

public record DailyUserTaskStatsReport(
        String email,
        Long completedTodayTasksCount,
        Long uncompletedTasksCount
) {
}
