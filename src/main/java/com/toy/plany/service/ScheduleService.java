package com.toy.plany.service;

import com.toy.plany.dto.response.event.ScheduleResponse;

import java.util.List;

public interface ScheduleService {
    ScheduleResponse readSchedule(Long scheduleId);

    List<ScheduleResponse> readScheduleListByUser(Long userId);

    List<ScheduleResponse> readScheduleListByUserList(List<Long> userIdList);
}
