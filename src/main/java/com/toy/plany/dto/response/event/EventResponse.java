package com.toy.plany.dto.response.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.toy.plany.entity.Event;
import com.toy.plany.entity.Schedule;
import com.toy.plany.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class EventResponse {
    @NotNull
    private Long eventId;

    @NotBlank
    private String title;

    private String description;

    private String date;

    private Integer day;

    private String startTime;

    private String endTime;

    private AttendantResponse organizer;

    private List<AttendantResponse> attendants;

    @Builder
    public EventResponse(Long eventId, String title, String description, String date, Integer day, String startTime, String endTime, AttendantResponse organizer, List<AttendantResponse> attendants) {
        this.eventId = eventId;
        this.title = title;
        this.description = description;
        this.date = date;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.organizer = organizer;
        this.attendants = attendants;
    }

    static public EventResponse from(Event event) {
        List<AttendantResponse> attendantsList = new ArrayList<>();
        for (Schedule schedule : event.getScheduleList()) {
            User user = schedule.getUser();
            attendantsList.add(AttendantResponse.from(user));
        }
        return EventResponse.builder()
                .eventId(event.getId())
                .title(event.getTitle())
                .day(event.getStartTime().getDayOfWeek().getValue())
                .date(event.getStartTime().format(DateTimeFormatter.ofPattern("yyyyMMdd")))
                .startTime(event.getStartTime().format(DateTimeFormatter.ofPattern("HHmm")))
                .endTime(event.getEndTime().format(DateTimeFormatter.ofPattern("HHmm")))
                .organizer(AttendantResponse.from(event.getOrganizer()))
                .attendants(attendantsList)
                .build();
    }
}
