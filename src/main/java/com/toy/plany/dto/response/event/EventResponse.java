package com.toy.plany.dto.response.event;

import com.toy.plany.dto.response.admin.UserResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class EventResponse {
    private Long eventId;
    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private UserResponse organizer;
    private List<UserResponse> attendances;

    @Builder
    public EventResponse(Long eventId, String title, String description, LocalDateTime startTime, LocalDateTime endTime, UserResponse organizer, List<UserResponse> attendances) {
        this.eventId = eventId;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.organizer = organizer;
        this.attendances = attendances;
    }
}
