package com.toy.plany.dto.response.event;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class ScheduleResponse {
    @NotNull
    private Long scheduleId;

    @NotNull
    private EventResponse event;

    @Builder
    public ScheduleResponse(Long scheduleId, EventResponse event) {
        this.scheduleId = scheduleId;
        this.event = event;
    }
}
