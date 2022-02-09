package com.toy.plany.service;

import com.toy.plany.dto.response.event.ScheduleResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{
    @Override
    public ScheduleResponse readSchedule(Long scheduleId) {
        return null;
    }

    @Override
    public List<ScheduleResponse> readScheduleListByUser(Long userId) {
        return null;
    }

    @Override
    public List<ScheduleResponse> readScheduleListByUserList(List<Long> userIdList) {
        return null;
    }
}
