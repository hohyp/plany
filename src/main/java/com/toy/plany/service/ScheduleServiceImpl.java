package com.toy.plany.service;

import com.toy.plany.dto.response.event.EventResponse;
import com.toy.plany.dto.response.event.EventUserResponse;
import com.toy.plany.dto.response.event.ScheduleResponse;
import com.toy.plany.entity.Event;
import com.toy.plany.entity.Schedule;
import com.toy.plany.entity.User;
import com.toy.plany.exception.exceptions.ScheduleNotFoundException;
import com.toy.plany.repository.ScheduleRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{

    private ScheduleRepo scheduleRepo;

    public ScheduleServiceImpl(ScheduleRepo scheduleRepo) {
        this.scheduleRepo = scheduleRepo;
    }

    @Override
    public ScheduleResponse readSchedule(Long scheduleId) {
        Schedule schedule = findScheduleById(scheduleId);
        return createScheduleDto(schedule);
    }

    private Schedule findScheduleById(Long scheduleId){
        return scheduleRepo.findById(scheduleId).orElseThrow(ScheduleNotFoundException::new);
    }

    @Override
    public List<ScheduleResponse> readScheduleListByUser(Long userId) {
        return null;
    }

    @Override
    public List<ScheduleResponse> readScheduleListByUserList(List<Long> userIdList) {
        return null;
    }

    private ScheduleResponse createScheduleDto(Schedule schedule){
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
