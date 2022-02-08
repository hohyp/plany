package com.toy.plany.dto.request.event;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class EventCreateRequest {
    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long organizer;
    private List<Long> attendances;
    private String location;

    @Builder
    public EventCreateRequest(String title, String description, LocalDateTime startTime, LocalDateTime endTime, Long organizer, List<Long> attendances, String location) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.organizer = organizer;
        this.attendances = attendances;
        this.location = location;
    }
}
