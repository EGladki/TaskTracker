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
                    " WHERE t.status != 'COMPLETED'")
    List<EmailTaskTitleStatusReport> findEmailTaskTitleStatusReport();

    // select
    //    u.email,
    //    count(*) filter ( where t.status != 'COMPLETED' ) as uncompleted_tasks_count,
    //    count(*) filter ( where t.status = 'COMPLETED' and t.completed_at >= current_date) as completed_today_tasks_count
    //from users u join public.tasks t on u.id = t.user_id
    //group by u.emai

}
