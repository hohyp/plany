package com.toy.plany.service;

import com.toy.plany.dto.response.event.ScheduleByUserResponse;

import java.util.List;

public interface ScheduleService {
    ScheduleByUserResponse readScheduleListByUser(Long userId);

    List<ScheduleByUserResponse> readScheduleListByUserList(List<Long> userIdList);

    Boolean deleteSchedule(Long userId, Long scheduleId);
}
