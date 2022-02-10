package com.toy.plany.dto.response.event;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UserScheduleResponse {
    private String name;
    private Long userId;
    private List<ScheduleResponse> scheduleResponseList;

    @Builder
    public UserScheduleResponse(String name, Long userId, List<ScheduleResponse> scheduleResponseList) {
        this.name = name;
        this.userId = userId;
        this.scheduleResponseList = scheduleResponseList;
    }
}
