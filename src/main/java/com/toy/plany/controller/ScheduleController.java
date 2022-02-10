package com.toy.plany.controller;

import com.toy.plany.dto.response.event.ScheduleResponse;
import com.toy.plany.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("schedule")
public class ScheduleController {
    private ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping
    public ResponseEntity<ScheduleResponse> readSchedule(@RequestParam Long scheduleId){
        ScheduleResponse res = scheduleService.readSchedule(scheduleId);
        return ResponseEntity.ok(res);
    }
}
