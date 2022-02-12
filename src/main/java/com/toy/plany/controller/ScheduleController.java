package com.toy.plany.controller;

import com.toy.plany.dto.response.event.ScheduleByUserResponse;
import com.toy.plany.service.ScheduleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('USER')")
    @ApiOperation(value = "스케줄 조회", notes = "user id로 받은 유저 한명의 스케줄 리스트를 조회한다")
    public ResponseEntity<ScheduleByUserResponse> readScheduleList(@RequestParam Long userId) {
        ScheduleByUserResponse res = scheduleService.readScheduleListByUser(userId);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/list/user-list")
    @PreAuthorize("hasAnyRole('USER')")
    @ApiOperation(value = "여러 유저의 스케줄 조회", notes = "user id list를 기반으로 여러명의 스케줄 목록을 조회한다")
    public ResponseEntity<List<ScheduleByUserResponse>> readScheduleListByUserList(@RequestParam(value = "userIdList") List<Long> userIdList) {
        List<ScheduleByUserResponse> res = scheduleService.readScheduleListByUserList(userIdList);
        return ResponseEntity.ok(res);
    }
}
