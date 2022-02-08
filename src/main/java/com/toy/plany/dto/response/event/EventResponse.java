package com.toy.plany.dto.response.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.toy.plany.dto.response.admin.UserResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
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

    @NotNull
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    private LocalDateTime startTime;

    @NotNull
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    private LocalDateTime endTime;

    @NotNull
    private EventUserResponse organizer;

    @NotNull
    private List<EventUserResponse> attendances;

    @Builder
    public EventResponse(Long eventId, String title, String description, LocalDateTime startTime, LocalDateTime endTime, EventUserResponse organizer, List<EventUserResponse> attendances) {
        this.eventId = eventId;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.organizer = organizer;
        this.attendances = attendances;
    }
}
