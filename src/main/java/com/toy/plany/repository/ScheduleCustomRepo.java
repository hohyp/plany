package com.toy.plany.repository;

import com.toy.plany.dto.dtos.FilteredEventDto;
import com.toy.plany.entity.Schedule;
import com.toy.plany.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleCustomRepo {
    List<FilteredEventDto> getEventInfo(Long userId, LocalDate startDate, LocalDate endDate);
}
