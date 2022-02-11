package com.toy.plany.dto.response.event;

import com.toy.plany.dto.dtos.FilteredEventDto;
import com.toy.plany.entity.Event;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EventInfoResponse {
    private Long eventId;
    private String title;
    private Integer day;
    private String date;
    private String startTime;
    private String endTime;

    @Builder
    public EventInfoResponse(Long eventId, String title, Integer day, String date, String startTime, String endTime) {
        this.eventId = eventId;
        this.title = title;
        this.day = day;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static EventInfoResponse from(Event event){
        return EventInfoResponse.builder()
                .eventId(event.getId())
                .title(event.getTitle())
                .startTime(event.getStartTime().toString())
                .endTime(event.getEndTime().toString())
                .build();
    }

    public static EventInfoResponse from(FilteredEventDto dto){
        return EventInfoResponse.builder()
                .eventId(dto.getEventId())
                .title(dto.getTitle())
                .startTime(dto.getStartTime().toString())
                .endTime(dto.getEndTime().toString())
                .build();
    }
}
