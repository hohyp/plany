package com.toy.plany.controller;

import com.toy.plany.dto.request.user.UpdatePasswordRequest;
import com.toy.plany.dto.response.admin.UserResponse;
import com.toy.plany.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin("*")
@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PatchMapping("/slack")
    @PreAuthorize("hasAnyRole('USER')")
    @ApiOperation(value = "슬랙 아이디 등록", notes = "유저의 슬랙 아이디를 등록한다")
    public ResponseEntity<UserResponse> insertSlackUid(@RequestParam Long userId, @RequestParam String slackUid){
        UserResponse res = userService.insertSlackUid(userId, slackUid);
        return ResponseEntity.ok(res);
    }

    @PatchMapping("/password")
    @PreAuthorize("hasAnyRole('USER')")
    @ApiOperation(value = "비밀번호 변경", notes = " request body field 수정 필요")
    public ResponseEntity<UserResponse> updatePassword(@RequestParam Long userId, @Valid @RequestBody UpdatePasswordRequest request){
        UserResponse res = userService.updatePassword(userId, request);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/department-user")
    @ApiOperation(value = "부서 구성원 조회", notes = "부서 id 를 기반으로 부서의 구성원 list를 조회한다")
    public ResponseEntity<List<UserResponse>> readUserListByDepartment(@RequestParam Long departmentId) {
        List<UserResponse> res = userService.readMyDepartmentUser(departmentId);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/auto-complete")
    @ApiOperation(value = "유저 이름 검색", notes = "유저 아이디랑 이름 반환")
    public ResponseEntity<List<UserResponse>> readAutoCompleteUserList(@RequestParam String keyword){
        List<UserResponse> res = userService.readAutoCompleteUserList(keyword);
        return ResponseEntity.ok(res);
    }
}
