package com.gladkiei.tasktracker.controllers;

import com.gladkiei.tasktracker.dtos.report.EmailTaskTitleStatusReport;
import com.gladkiei.tasktracker.security.UserDetailsImpl;
import com.gladkiei.tasktracker.services.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/internal")
@RequiredArgsConstructor
public class InternalServicesController {

    private final ReportService reportService;

    @GetMapping("/tasks")
    public List<EmailTaskTitleStatusReport> tasks() {
        return reportService.getReport();
    }
}
