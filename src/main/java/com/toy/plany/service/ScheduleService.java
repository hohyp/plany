package com.toy.plany.service;

import com.toy.plany.dto.response.event.ScheduleResponse;
import com.toy.plany.dto.response.event.UserScheduleResponse;

import java.util.List;

public interface ScheduleService {
    ScheduleResponse readSchedule(Long scheduleId);

    UserScheduleResponse readScheduleListByUser(Long userId);

    List<UserScheduleResponse> readScheduleListByUserList(List<Long> userIdList);
}
