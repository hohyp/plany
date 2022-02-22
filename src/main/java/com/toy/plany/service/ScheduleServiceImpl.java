package com.toy.plany.service;

import com.toy.plany.dto.dtos.FilteredEventDto;
import com.toy.plany.dto.request.schedule.ReadScheduleRequest;
import com.toy.plany.dto.response.event.EventInfoResponse;
import com.toy.plany.dto.response.event.ScheduleByUserResponse;
import com.toy.plany.entity.Event;
import com.toy.plany.entity.Schedule;
import com.toy.plany.entity.User;
import com.toy.plany.exception.exceptions.*;
import com.toy.plany.repository.EventRepo;
import com.toy.plany.repository.ScheduleRepo;
import com.toy.plany.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private UserRepo userRepo;
    private ScheduleRepo scheduleRepo;
    private EventRepo eventRepo;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepo scheduleRepo, UserRepo userRepo, EventRepo eventRepo) {
        this.scheduleRepo = scheduleRepo;
        this.userRepo = userRepo;
        this.eventRepo = eventRepo;
    }

    @Override
    @Transactional
    public ScheduleByUserResponse readScheduleListByUser(Long userId, ReadScheduleRequest request) {
        User user = getUserById(userId);
        List<EventInfoResponse> eventInfoResponseList = getEventInfoResponseList(userId, request.getStartDate(), request.getEndDate());

        return ScheduleByUserResponse.from(user, eventInfoResponseList);
    }


    @Transactional(readOnly = true)
    public User getUserById(Long userId) {
        return userRepo.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<ScheduleByUserResponse> readScheduleListByUserList(List<Long> userIdList, ReadScheduleRequest request) {
        List<ScheduleByUserResponse> res = new ArrayList<>();
        for (Long id : userIdList) {
            res.add(readScheduleListByUser(id, request));
        }
        return res;
    }

    @Override
    @Transactional
    public Boolean deleteSchedule(Long userId, Long eventId) {
        try {
            Event event = getEventById(eventId);
            User user = getUserById(userId);
            Schedule schedule = getSchedule(user, event);
            deleteScheduleFromRepo(schedule);
            return true;
        } catch (Exception e) {
            throw new DeleteFailException();
        }
    }

    @Transactional
    public Schedule getSchedule(User user, Event event) {
        return scheduleRepo.findSchedule(user, event).orElseThrow(ScheduleNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public Event getEventById(Long eventId) {
        return eventRepo.findById(eventId).orElseThrow(EventNotFoundException::new);
    }


    @Transactional
    public void deleteScheduleFromRepo(Schedule schedule) {
        try {
            scheduleRepo.delete(schedule);
        } catch (Exception e) {
            throw new DeleteFailException();
        }
    }

    @Transactional
    public List<EventInfoResponse> getEventInfoResponseList(Long userId, LocalDate startDate, LocalDate endDate) {
        return scheduleRepo.getEventInfo(userId, startDate, endDate).stream().sorted(Comparator.comparing(FilteredEventDto::getStartTime)).map(it -> EventInfoResponse.from(it)).collect(Collectors.toList());
    }
}
