package com.toy.plany.service;

import com.toy.plany.entity.Schedule;


public interface AlarmService {
    Boolean sendCreatedAlarm(Schedule schedule);

    Boolean sendReminderAlarm(Reminder reminder);

}
