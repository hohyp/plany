package com.toy.plany.service;

import com.toy.plany.dto.dtos.FilteredEventDto;
import com.toy.plany.dto.response.event.EventInfoResponse;
import com.toy.plany.dto.response.event.EventResponse;
import com.toy.plany.dto.response.event.AttendantResponse;
import com.toy.plany.dto.response.event.ScheduleByUserResponse;
import com.toy.plany.entity.Event;
import com.toy.plany.entity.Schedule;
import com.toy.plany.entity.User;
import com.toy.plany.exception.exceptions.UserNotFoundException;
import com.toy.plany.repository.EventRepo;
import com.toy.plany.repository.ScheduleRepo;
import com.toy.plany.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public ScheduleByUserResponse readScheduleListByUser(Long userId) {
        User user = findUserById(userId);
        List<EventInfoResponse> eventInfoResponseList = getEventInfoResponseList(userId);

        return ScheduleByUserResponse.from(user, eventInfoResponseList);
    }

    private List<Schedule> getScheduleListByUser(User user) {
        return scheduleRepo.findScheduleByUser(user);
    }

    private User findUserById(Long userId) {
        return userRepo.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<ScheduleByUserResponse> readScheduleListByUserList(List<Long> userIdList) {
        List<ScheduleByUserResponse> res = new ArrayList<>();
        for(Long id : userIdList){
            res.add(readScheduleListByUser(id));
        }
        return res;
    }

    private List<User> getUserList(List<Long> userIdList) {
        return userRepo.findUserByIdList(userIdList);
    }

    private List<Schedule> getScheduleListByUserList(List<User> userList) {
        return scheduleRepo.findScheduleByUserList(userList);
    }

    @Override
    public Boolean deleteSchedule(Long userId, Long scheduleId) {
        return null;
    }

    private List<EventInfoResponse> getEventInfoResponseList(Long userId) {
        List<Long> eventIds = scheduleRepo.findEventIdListByUser(userId);
        System.out.println(eventIds);
        return eventRepo.getEventInfo(eventIds).stream().map(it -> EventInfoResponse.from(it)).collect(Collectors.toList());
    }

    private List<AttendantResponse> createAttendantsList(List<Schedule> scheduleList) {
        List<AttendantResponse> attendantsList = new ArrayList<>();
        for (Schedule schedule : scheduleList) {
            User user = schedule.getUser();
            attendantsList.add(AttendantResponse.from(user));
        }
        return attendantsList;
    }
}
