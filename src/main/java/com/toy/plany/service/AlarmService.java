package com.toy.plany.service;

import com.toy.plany.entity.Reminder;
import com.toy.plany.entity.Schedule;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;


public interface AlarmService {
    Boolean sendCreatedAlarm(Schedule schedule);

    Boolean sendReminderAlarm(Reminder reminder);

}
