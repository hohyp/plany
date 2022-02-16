package com.toy.plany.service;

import com.toy.plany.entity.Event;
import com.toy.plany.entity.Schedule;
import com.toy.plany.entity.enums.AlarmStatus;

public interface SendAlarmService {
    void sendAlarm(Event event);
    void sendAlarm(Schedule schedule, AlarmStatus status);
}
