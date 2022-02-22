package com.toy.plany.dto.request.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class EventCreateRequest {
    private String title;

    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String date;

    private String startHour;

    private String startMinute;

    private String endHour;

    private String endMinute;

    private Long organizer;

    private List<Long> attendances;

    private String location;

    @Builder
    public EventCreateRequest(String title, String description, String date, String startHour, String startMinute, String endHour, String endMinute, Long organizer, List<Long> attendances, String location) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
        this.organizer = organizer;
        this.attendances = attendances;
        this.location = location;
    }
}
