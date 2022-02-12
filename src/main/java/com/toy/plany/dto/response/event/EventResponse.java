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

    //TODO date, startTime, endTime 분리
//
//    private String date;
//
//    private String startTime;
//
//    private String endTime;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime startTime;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime endTime;

    private AttendantResponse organizer;

    private List<AttendantResponse> attendants;


    @Builder
    public EventResponse(Long eventId, String title, String description, LocalDateTime startTime, LocalDateTime endTime, AttendantResponse organizer, List<AttendantResponse> attendants) {
        this.eventId = eventId;
        this.title = title;
        this.description = description;
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
                .description(event.getDescription())
                .organizer(AttendantResponse.from(event.getOrganizer()))
                .attendants(attendantsList)
                .startTime(event.getStartTime())
                .endTime(event.getEndTime())
                .build();
    }
}
