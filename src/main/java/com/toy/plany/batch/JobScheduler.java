package com.toy.plany.batch;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JobScheduler {
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private ReminderJobConfig reminderJob;

    @Scheduled(cron = "30 25,55 * * * *", zone = "Asia/Seoul")
    public void jobSchedule() throws JobParametersInvalidException, JobExecutionAlreadyRunningException,
            JobRestartException, JobInstanceAlreadyCompleteException {

        Map<String, JobParameter> jobParametersMap = new HashMap<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime time = LocalDateTime.now();

        String date = formatter.format(time);

        jobParametersMap.put("date", new JobParameter(date));

        JobParameters parameters = new JobParameters(jobParametersMap);

        JobExecution jobExecution = jobLauncher.run(reminderJob.reminderJob(), parameters);
    }
}
