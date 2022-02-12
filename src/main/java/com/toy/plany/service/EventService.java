package com.toy.plany.service;

import com.toy.plany.dto.request.event.EventCreateRequest;
import com.toy.plany.dto.response.event.EventResponse;

public interface EventService {
    EventResponse createEvent(Long userId, EventCreateRequest request);



    Boolean deleteEvent(Long userId, Long eventId);
}
