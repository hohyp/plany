package com.toy.plany.controller;

import com.toy.plany.dto.response.event.ScheduleByUserResponse;
import com.toy.plany.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@CrossOrigin("*")
@Controller
@RequestMapping("schedule")
public class ScheduleController {
    private ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/list")
    public ResponseEntity<ScheduleByUserResponse> readScheduleList(@RequestParam Long userId) {
        ScheduleByUserResponse res = scheduleService.readScheduleListByUser(userId);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/list/user-list")
    public ResponseEntity<List<ScheduleByUserResponse>> readScheduleListByUserList(@RequestParam(value = "userIdList") List<Long> userIdList) {
        List<ScheduleByUserResponse> res = scheduleService.readScheduleListByUserList(userIdList);
        return ResponseEntity.ok(res);
    }
}
