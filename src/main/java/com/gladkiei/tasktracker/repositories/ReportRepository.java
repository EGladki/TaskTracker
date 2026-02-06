package com.gladkiei.tasktracker.repositories;

import com.gladkiei.tasktracker.dtos.report.EmailTaskTitleStatusReport;
import com.gladkiei.tasktracker.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface ReportRepository extends Repository<User, Long> {

    @Query(value =
            "SELECT new com.gladkiei.tasktracker.dtos.report.EmailTaskTitleStatusReport(u.email, t.title, t.status) " +
                    "FROM User u " +
                    "JOIN Task t on u.id = t.user.id" +
                    " WHERE t.status != 'DONE'")
    List<EmailTaskTitleStatusReport> findEmailTaskTitleStatusReport();

}
