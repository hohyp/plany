package com.toy.plany.dto.request.event;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class EventCreateRequest {
    private String title;
    private String description;
    private LocalDate date;
    private Integer day;
    private String startHour;
    private String startMinute;
    private String endHour;
    private String endMinute;
    private Long organizer;
    private List<Long> attendances;
    private String location;

    @Builder
    public EventCreateRequest(String title, String description, LocalDate date, Integer day, String startHour, String startMinute, String endHour, String endMinute, Long organizer, List<Long> attendances, String location) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.day = day;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
        this.organizer = organizer;
        this.attendances = attendances;
        this.location = location;
    }
}
