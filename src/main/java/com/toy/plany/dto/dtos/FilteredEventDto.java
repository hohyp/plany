package com.toy.plany.dto.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class FilteredEventDto {
    private Long eventId;
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Builder
    public FilteredEventDto(Long eventId, String title, LocalDateTime startTime, LocalDateTime endTime) {
        this.eventId = eventId;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
