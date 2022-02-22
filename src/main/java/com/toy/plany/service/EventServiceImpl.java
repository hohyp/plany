package com.toy.plany.service;

import com.toy.plany.dto.request.event.EventCreateRequest;
import com.toy.plany.dto.response.event.EventResponse;
import com.toy.plany.entity.Event;
import com.toy.plany.entity.Schedule;
import com.toy.plany.entity.User;
import com.toy.plany.entity.enums.AlarmStatus;
import com.toy.plany.exception.exceptions.*;
import com.toy.plany.repository.EventRepo;
import com.toy.plany.repository.ScheduleRepo;
import com.toy.plany.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService, SendAlarmService {

    final private String SLACK_POST_MESSAGE_URL = "https://slack.com/api/chat.postMessage";

    @Value("${slack.token}")
    private String SLACK_BOT_TOKEN;

    private EventRepo eventRepo;
    private UserRepo userRepo;
    private ScheduleRepo scheduleRepo;

    @Autowired
    public EventServiceImpl(EventRepo eventRepo, UserRepo userRepo, ScheduleRepo scheduleRepo) {
        this.eventRepo = eventRepo;
        this.userRepo = userRepo;
        this.scheduleRepo = scheduleRepo;
    }

    /**
     * 1. 시작시간, 종료시간 검증
     * 2. 이벤트 생성
     * 3. 이벤트 참여자들의 일정 생성
     * 4. 이벤트에 scheduleList 업데이트
     * 5. scheduleList 기반으로 slack 알람 전송
     * 6. eventResponse 생성하여 리턴
     *
     * @param userId
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EventResponse createEvent(Long userId, EventCreateRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(request.getDate(), formatter);

        User organizer = findUserById(userId);
        LocalDateTime startTime = date.atTime(Integer.valueOf(request.getStartHour()), Integer.valueOf(request.getStartMinute()));
        LocalDateTime endTime = date.atTime(Integer.valueOf(request.getEndHour()), Integer.valueOf(request.getEndMinute()));
        //TODO 날짜 검증하기
        Event event = Event.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .organizer(organizer)
                .startTime(startTime)
                .endTime(endTime)
                .status(AlarmStatus.CREATED)
                .location(request.getLocation())
                .build();
        Event savedEvent = saveEvent(event);
        List<Schedule> scheduleList = createScheduleList(savedEvent, request.getAttendances());
        savedEvent.updateScheduleList(scheduleList);
        return EventResponse.from(savedEvent);
    }

    @Override
    @Transactional
    public EventResponse readEvent(Long eventId) {
        Event event = findEventById(eventId);
        return EventResponse.from(event);
    }

    @Transactional
    private Event saveEvent(Event event) {
        try {
            return eventRepo.save(event);
        } catch (Exception e) {
            throw new SaveFailException();
        }
    }

    @Transactional
    private List<Schedule> createScheduleList(Event event, List<Long> attendancesList) {
        List<Schedule> scheduleList = new ArrayList<>();
        for (Long userId : attendancesList) {
            User user = findUserById(userId);
            Schedule schedule = createSchedule(user, event);
            scheduleList.add(schedule);
        }
        return scheduleList;
    }


    public Schedule createSchedule(User user, Event event) {
        Schedule schedule = Schedule.builder()
                .user(user)
                .event(event)
                .title(event.getTitle())
                .status(AlarmStatus.CREATED)
                .remindTime(event.getStartTime().minusMinutes(5))
                .startTime(event.getStartTime())
                .endTime(event.getEndTime())
                .build();
        return saveSchedule(schedule);
    }

    @Transactional
    public Schedule saveSchedule(Schedule schedule) {
        try {
            sendAlarm(schedule, AlarmStatus.CREATED);
            return scheduleRepo.save(schedule);
        } catch (Exception e) {
            throw new SaveFailException();
        }
    }

    private String createAlarmMessage(String slackUid, String scheduleTitle, String reminderType, String from, String to) {
        String body = "{\"channel\": \"" + slackUid + "\", \"text\" : \"" + "Event " + reminderType + " : " + scheduleTitle + " from " + from + " to " + to + "\"}";
        return body;
    }

    @Transactional(readOnly = true)
    public User findUserById(Long userId) {
        return userRepo.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    @Override
    @Transactional
    public Boolean deleteEvent(Long userId, Long eventId) {
        Event event = findEventById(eventId);
        User user = findUserById(userId);
        if (validateOrganizer(event.getOrganizer(), user)) {
            sendAlarm(event);
            deleteEventFromRepo(event);
            return true;
        } else
            throw new InvalidOrganizerException();
    }

    @Override
    public void sendAlarm(Event event) {
        List<Schedule> attendants = event.getScheduleList();
        for (Schedule s : attendants) {
            sendAlarm(s, AlarmStatus.CANCELED);
        }
    }


    @Override
    public void sendAlarm(Schedule schedule, AlarmStatus status) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH시 mm분");
        String startDateTime = schedule.getStartTime().format(formatter);
        String endDateTime = schedule.getEndTime().format(formatter);
        String message = createAlarmMessage(schedule.getUser().getSlackUid(), schedule.getTitle(), status.getValue(), startDateTime, endDateTime);
        sendSlackDM(message);
    }

    @Transactional
    public void deleteEventFromRepo(Event event) {
        try {
            eventRepo.delete(event);
        } catch (Exception e) {
            throw new DeleteFailException();
        }
    }

    private Boolean validateOrganizer(User organizer, User userId) {
        if (organizer == userId)
            return true;
        return false;
    }

    @Transactional(readOnly = true)
    private Event findEventById(Long eventId) {
        return eventRepo.findById(eventId).orElseThrow(EventNotFoundException::new);
    }


    @Transactional
    public void sendSlackDM(String body) {
        try {

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + SLACK_BOT_TOKEN);
            headers.add("Content-type", "application/json; charset=utf-8");

            HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.exchange(SLACK_POST_MESSAGE_URL, HttpMethod.POST, requestEntity, String.class);

            //TODO response 검사하여 예외처리

        } catch (Exception e) {
            //TODO 예외처리
            System.out.println("에러 발생");
        }
    }

}
