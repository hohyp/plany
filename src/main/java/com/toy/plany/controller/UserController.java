package com.toy.plany.controller;

import com.toy.plany.dto.request.user.LoginRequest;
import com.toy.plany.dto.request.user.UpdatePasswordRequest;
import com.toy.plany.dto.response.admin.UserResponse;
import com.toy.plany.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest request){
        UserResponse res = userService.login(request);
        return ResponseEntity.ok(res);
    }

    @PatchMapping("/slack")
    public ResponseEntity<UserResponse> insertSlackUid(@RequestParam Long userId, @RequestParam String slackUid){
        UserResponse res = userService.insertSlackUid(userId, slackUid);
        return ResponseEntity.ok(res);
    }

    @PatchMapping("/password")
    public ResponseEntity<UserResponse> updatePassword(@RequestParam Long userId, @RequestBody UpdatePasswordRequest request){
        UserResponse res = userService.updatePassword(userId, request);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/my-department-user")
    public ResponseEntity<List<UserResponse>> readUserListByDepartment(@RequestParam Long departmentId){
        List<UserResponse> res = userService.readMyDepartmentUser(departmentId);
        return ResponseEntity.ok(res);
    }
}
