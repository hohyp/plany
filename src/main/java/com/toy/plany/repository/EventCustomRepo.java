package com.toy.plany.repository;

import com.toy.plany.dto.dtos.FilteredEventDto;

import java.util.List;

public interface EventCustomRepo {
    List<FilteredEventDto> getEventInfo(List<Long> eventIdList);
}
