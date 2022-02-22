package com.toy.plany.controller;

import com.toy.plany.dto.request.event.EventCreateRequest;
import com.toy.plany.dto.response.event.EventResponse;
import com.toy.plany.service.EventService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
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
    @PreAuthorize("hasAnyRole('USER')")
    @ApiOperation(value = "이벤트 등록", notes = "user id로 넘어온 유저를 주최자로 하여 body로 들어온 이벤트 정보를 기반으로 이벤트를 생성한다.")
    public ResponseEntity<EventResponse> createEvent(@RequestParam Long userId, @Valid @RequestBody EventCreateRequest request) {
        EventResponse res = eventService.createEvent(userId, request);
        return ResponseEntity.ok(res);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER')")
    @ApiOperation(value = "이벤트 조회", notes = "event id를 받아서 해당 이벤트의 정보를 조회한다 (title, description, date, time, organizer, attendants")
    public ResponseEntity<EventResponse> readEvent(@RequestParam Long eventId) {
        EventResponse res = eventService.readEvent(eventId);
        return ResponseEntity.ok(res);
    }


    @DeleteMapping
    @PreAuthorize("hasAnyRole('USER')")
    @ApiOperation(value = "이벤트 삭제", notes = "user id가 event id 의 event의 주최자와 동일하면 이벤트를 삭제한다")
    public ResponseEntity<Boolean> deleteEvent(@RequestParam Long userId, @RequestParam Long eventId) {
        Boolean res = eventService.deleteEvent(userId, eventId);
        return ResponseEntity.ok(res);
    }
}
