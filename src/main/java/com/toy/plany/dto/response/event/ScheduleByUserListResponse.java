package com.toy.plany.dto.response.event;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ScheduleByUserListResponse {
    private String name;
    private String userId;
    private List<ScheduleResponse> scheduleResponseList;
}
