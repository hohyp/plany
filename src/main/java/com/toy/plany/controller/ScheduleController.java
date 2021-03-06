package com.toy.plany.controller;

import com.toy.plany.dto.request.schedule.ReadScheduleRequest;
import com.toy.plany.dto.response.event.ScheduleByUserResponse;
import com.toy.plany.service.ScheduleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@Controller
@RequestMapping("schedule")
public class ScheduleController {
    private ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/list")
    @PreAuthorize("hasAnyRole('USER')")
    @ApiOperation(value = "스케줄 조회", notes = "user id로 받은 유저 한명의 스케줄 리스트를 조회한다")
    public ResponseEntity<ScheduleByUserResponse> readScheduleList(@RequestParam Long userId,  @Valid @RequestBody ReadScheduleRequest request) {
        ScheduleByUserResponse res = scheduleService.readScheduleListByUser(userId, request);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/list/user-list")
    @PreAuthorize("hasAnyRole('USER')")
    @ApiOperation(value = "여러 유저의 스케줄 조회", notes = "user id list를 기반으로 여러명의 스케줄 목록을 조회한다")
    public ResponseEntity<List<ScheduleByUserResponse>> readScheduleListByUserList(@RequestParam(value = "userIdList") List<Long> userIdList, @Valid @RequestBody ReadScheduleRequest request) {
        List<ScheduleByUserResponse> res = scheduleService.readScheduleListByUserList(userIdList, request);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping
    @PreAuthorize("hasAnyRole('USER')")
    @ApiOperation(value = "스케줄 삭제", notes = "스케줄 삭제")
    public ResponseEntity<Boolean> deleteSchedule(@RequestParam Long userId, @RequestParam Long eventId) {
        Boolean res = scheduleService.deleteSchedule(userId, eventId);
        return ResponseEntity.ok(res);
    }
}
