package com.toy.plany.service;

import com.toy.plany.dto.response.event.EventResponse;
import com.toy.plany.dto.response.event.EventUserResponse;
import com.toy.plany.dto.response.event.ScheduleResponse;
import com.toy.plany.dto.response.event.UserScheduleResponse;
import com.toy.plany.entity.Event;
import com.toy.plany.entity.Schedule;
import com.toy.plany.entity.User;
import com.toy.plany.exception.exceptions.ScheduleNotFoundException;
import com.toy.plany.exception.exceptions.UserNotFoundException;
import com.toy.plany.repository.ScheduleRepo;
import com.toy.plany.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private UserRepo userRepo;
    private ScheduleRepo scheduleRepo;

    public ScheduleServiceImpl(ScheduleRepo scheduleRepo, UserRepo userRepo) {
        this.scheduleRepo = scheduleRepo;
        this.userRepo = userRepo;
    }

    @Override
    public ScheduleResponse readSchedule(Long scheduleId) {
        Schedule schedule = findScheduleById(scheduleId);
        return createScheduleDto(schedule);
    }

    private Schedule findScheduleById(Long scheduleId) {
        return scheduleRepo.findById(scheduleId).orElseThrow(ScheduleNotFoundException::new);
    }

    @Override
    public UserScheduleResponse readScheduleListByUser(Long userId) {
        User user = findUserById(userId);
        List<Schedule> scheduleList = getScheduleListByUser(user);
        return createUserScheduleDto(user, scheduleList);
    }

    private List<Schedule> getScheduleListByUser(User user) {
        return scheduleRepo.findScheduleByUser(user);
    }

    private User findUserById(Long userId) {
        return userRepo.findById(userId).orElseThrow(UserNotFoundException::new);
    }


    @Override
    public List<UserScheduleResponse> readScheduleListByUserList(List<Long> userIdList) {
        return null;
    }

    private UserScheduleResponse createUserScheduleDto(User user, List<Schedule> scheduleList) {
        return UserScheduleResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .scheduleResponseList(scheduleList.stream().map(schedule -> createScheduleDto(schedule)).collect(Collectors.toList()))
                .build();
    }

    private ScheduleResponse createScheduleDto(Schedule schedule) {
        EventResponse eventResponse = createEventDto(schedule.getEvent());
        return ScheduleResponse.builder()
                .scheduleId(schedule.getId())
                .event(eventResponse)
                .build();
    }

    private EventResponse createEventDto(Event event) {
        return EventResponse.builder()
                .eventId(event.getId())
                .title(event.getTitle())
                .description(event.getDescription())
                .organizer(createEventUserDto(event.getOrganizer()))
                .attendances(createAttendantsList(event.getScheduleList()))
                .startTime(event.getStartTime())
                .endTime(event.getEndTime())
                .build();
    }

    private List<EventUserResponse> createAttendantsList(List<Schedule> scheduleList) {
        List<EventUserResponse> attendantsList = new ArrayList<>();
        for (Schedule schedule : scheduleList) {
            User user = schedule.getUser();
            attendantsList.add(createEventUserDto(user));
        }
        return attendantsList;
    }

    private EventUserResponse createEventUserDto(User user) {
        return EventUserResponse.builder()
                .id(user.getId())
                .employeeNum(user.getEmployeeNum())
                .name(user.getName())
                .color(user.getColor().getCode())
                .fontColor(user.getColor().getFontColor().getCode())
                .department(user.getDepartment().getName())
                .position(user.getPosition())
                .build();
    }
}
