package com.toy.plany.dto.response.event;

import com.toy.plany.dto.dtos.FilteredEventDto;
import com.toy.plany.entity.Event;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class EventInfoResponse {
    /*
    참석자 정보 없이 반환하는 이벤트 dto
     */
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
        if(event.getStartTime().getDayOfYear() != event.getEndTime().getDayOfYear())
            return null;
        return EventInfoResponse.builder()
                .eventId(event.getId())
                .title(event.getTitle())
                .day(event.getStartTime().getDayOfWeek().getValue())
                .date(event.getStartTime().format(DateTimeFormatter.ofPattern("yyyyMMdd")))
                .startTime(event.getStartTime().format(DateTimeFormatter.ofPattern("HHmm")))
                .endTime(event.getEndTime().format(DateTimeFormatter.ofPattern("HHmm")))
                .build();
    }

    public static EventInfoResponse from(FilteredEventDto dto){
        return EventInfoResponse.builder()
                .eventId(dto.getEventId())
                .title(dto.getTitle())
                .day(dto.getStartTime().getDayOfWeek().getValue())
                .date(dto.getStartTime().format(DateTimeFormatter.ofPattern("yyyyMMdd")))
                .startTime(dto.getStartTime().format(DateTimeFormatter.ofPattern("HHmm")))
                .endTime(dto.getEndTime().format(DateTimeFormatter.ofPattern("HHmm")))
                .build();
    }
}
