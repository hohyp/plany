package com.toy.plany.dto.request.schedule;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ReadScheduleRequest {
    LocalDate startDate;
    LocalDate endDate;

    @Builder
    public ReadScheduleRequest(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
