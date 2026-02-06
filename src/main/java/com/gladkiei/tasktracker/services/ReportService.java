package com.gladkiei.tasktracker.services;

import com.gladkiei.tasktracker.dtos.report.EmailTaskTitleStatusReport;
import com.gladkiei.tasktracker.repositories.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;

    public List<EmailTaskTitleStatusReport> getReport() {
        return reportRepository.findEmailTaskTitleStatusReport();
    }
}
