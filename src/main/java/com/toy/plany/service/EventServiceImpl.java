package com.toy.plany.service;

import com.toy.plany.dto.request.event.EventCreateRequest;
import com.toy.plany.dto.response.event.EventResponse;
import com.toy.plany.dto.response.event.AttendantResponse;
import com.toy.plany.entity.Event;
import com.toy.plany.entity.Schedule;
import com.toy.plany.entity.User;
import com.toy.plany.entity.enums.EventStatus;
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
    public EventResponse createEvent(Long userId, EventCreateRequest request) {
        User organizer = findUserById(userId);
        Event event = Event.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .organizer(organizer)
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .status(EventStatus.CREATED)
                .build();
        Event savedEvent = saveEvent(event);
        List<Schedule> scheduleList = createScheduleList(savedEvent, request.getAttendances());
        savedEvent.updateScheduleList(scheduleList);
        sendAlarm(event);
        return EventResponse.from(savedEvent);
    }

    @Transactional
    private Event saveEvent(Event event) {
        try {
            return eventRepo.save(event);
        } catch (Exception e) {
            throw new SaveFailException();
        }
    }

    private void validateScheduleTime() {
        //TODO 스케줄 생성 전 가용시간 검증
    }

    private List<Schedule> createScheduleList(Event event, List<Long> attendancesList) {
        List<Schedule> scheduleList = new ArrayList<>();
        for (Long userId : attendancesList) {
            User user = findUserById(userId);
            validateScheduleTime();
            Schedule schedule = createSchedule(user, event);
            scheduleList.add(schedule);
        }
        return scheduleList;
    }


    private Schedule createSchedule(User user, Event event) {
        Schedule schedule = Schedule.builder()
                .user(user)
                .event(event)
                .build();
        return saveSchedule(schedule);
    }

    @Transactional
    private Schedule saveSchedule(Schedule schedule) {
        try {
            return scheduleRepo.save(schedule);
        } catch (Exception e) {
            throw new SaveFailException();
        }
    }

    @Override
    public void sendAlarm(Event event) {
        List<Schedule> scheduleList = event.getScheduleList();
        for (Schedule schedule : scheduleList) {
            sendSlackDM(schedule.getUser().getSlackUid(), event.getOrganizer().getName(), event.getTitle(), event.getStatus().getValue());
        }
    }

    @Transactional(readOnly = true)
    private User findUserById(Long userId) {
        return userRepo.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public Boolean deleteEvent(Long userId, Long eventId) {
        Event event = findEventById(eventId);
        User user = findUserById(userId);
        if (validateOrganizer(event.getOrganizer(), user)) {
            event.updateStatus(EventStatus.CANCELED);
            sendAlarm(event);
            deleteEventFromRepo(event);
            return true;
        } else
            throw new InvalidOrganizerException();
    }

    private void deleteEventFromRepo(Event event) {
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

    private void sendSlackDM(String slackUid, String organizerName, String scheduleTitle, String reminderType) {
        try {

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + SLACK_BOT_TOKEN);
            headers.add("Content-type", "application/json; charset=utf-8");

            //TODO from, to 추가하기
            String body = "{\"channel\": \"" + slackUid + "\", \"text\" : \"" + "Event " + reminderType + " By " + organizerName + " : " + scheduleTitle + "\"}";

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
