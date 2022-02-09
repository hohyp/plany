package com.toy.plany.controller;

import com.toy.plany.dto.request.event.EventCreateRequest;
import com.toy.plany.dto.response.event.EventResponse;
import com.toy.plany.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@CrossOrigin("*")
@Controller
@RequestMapping("/event")
public class EventController {
    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<EventResponse> createEvent(@RequestParam Long userId, @Valid @RequestBody EventCreateRequest request){
        EventResponse res = eventService.createEvent(userId, request);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteEvent(@RequestParam Long userId, @RequestParam Long eventId){
        Boolean res = eventService.deleteEvent(userId, eventId);
        return ResponseEntity.ok(res);
    }
}
