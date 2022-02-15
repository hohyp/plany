package com.toy.plany.controller;

import com.toy.plany.dto.request.admin.UserCreateRequest;
import com.toy.plany.dto.request.admin.UserUpdateRequest;
import com.toy.plany.dto.response.admin.DepartmentResponse;
import com.toy.plany.dto.response.admin.UserResponse;
import com.toy.plany.entity.Color;
import com.toy.plany.entity.enums.Colors;
import com.toy.plany.entity.enums.FontColor;
import com.toy.plany.service.AdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.http.HttpResponse;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/user")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "유저 생성", notes = "받은 유저 정보를 기반으로 유저를 생성한다")
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserCreateRequest request) {
        UserResponse res = adminService.createUser(request);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/user/list")
    @ApiOperation(value = "유저 조회", notes = "존재하는 모든 유저의 목록을 조회한다")
    public ResponseEntity<List<UserResponse>> readUserList() {
        List<UserResponse> res = adminService.readUserList();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @ApiOperation(value = "유저 조회", notes = "사원번호를 통해 한명의 유저를 조회한다")
    public ResponseEntity<UserResponse> readUserByEmployeeNum(@RequestParam String employeeNumber) {
        UserResponse res = adminService.readUserByEmployeeNumber(employeeNumber);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/user/search")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @ApiOperation(value = "유저 검색", notes = "이름 통해 유저를 검색한다")
    public ResponseEntity<List<UserResponse>> readUserByName(@RequestParam String userName) {
        List<UserResponse> res = adminService.readUserByName(userName);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/user")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "유저 수정", notes = "유저 id와 정보를 기반으로 유저를 수정한다")
    public ResponseEntity<UserResponse> updateUser(@RequestParam Long userId,@RequestBody @Valid UserUpdateRequest request) {
        UserResponse res = adminService.updateUser(userId, request);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/user")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "유저 삭제", notes = "유저 id를 통해 유저를 삭제한다")
    public ResponseEntity<Boolean> deleteUser(@RequestParam Long userId) {
        Boolean res = adminService.deleteUser(userId);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/user/list")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "유저 리스트 삭제", notes = "유저 id List를 통해 유저를 삭제한다")
    public ResponseEntity<Boolean> deleteUserList(@RequestParam(value = "userIdList") List<Long> userIdList) {
        Boolean res = adminService.deleteUser(userIdList);
        return ResponseEntity.ok(res);
    }


    @PostMapping("/department")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "부서 생성", notes = "부서 이름을 통해 부서를 생성한다")
    public ResponseEntity<DepartmentResponse> createDepartment(@RequestParam String name) {
        DepartmentResponse res = adminService.createDepartment(name);
        return ResponseEntity.ok(res);
    }


    @GetMapping("/department/list")
    @ApiOperation(value = "부서 조회", notes = "존재하는 모든 부서 목록을 조회한다")
    public ResponseEntity<List<DepartmentResponse>> readDepartmentList() {
        List<DepartmentResponse> res = adminService.readDepartmentList();
        return ResponseEntity.ok(res);
    }


    @DeleteMapping("/department")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "부서 삭제", notes = "부서 id를 통해 부서를 삭제한다")
    public ResponseEntity<Boolean> deleteDepartment(@RequestParam Long departmentId) {
        adminService.deleteDepartment(departmentId);
        return ResponseEntity.ok(true);
    }

    @PostMapping("/color")
    public ResponseEntity<Color> addColor(@RequestParam Colors color, @RequestParam FontColor fontColor){
        Color c = adminService.addColor(color, fontColor);
        return ResponseEntity.ok(c);
    }


}
