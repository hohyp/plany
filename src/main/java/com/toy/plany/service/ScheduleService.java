package com.toy.plany.service;

import com.toy.plany.dto.request.schedule.ReadScheduleRequest;
import com.toy.plany.dto.response.event.ScheduleByUserResponse;

import java.util.List;

public interface ScheduleService {
    ScheduleByUserResponse readScheduleListByUser(Long userId, ReadScheduleRequest request);

    List<ScheduleByUserResponse> readScheduleListByUserList(List<Long> userIdList, ReadScheduleRequest request);

    Boolean deleteSchedule(Long userId, Long eventId);
}
