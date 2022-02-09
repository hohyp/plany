package com.toy.plany.controller;

import com.toy.plany.dto.request.admin.UserCreateRequest;
import com.toy.plany.dto.response.admin.DepartmentResponse;
import com.toy.plany.dto.response.admin.UserResponse;
import com.toy.plany.service.AdminService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserCreateRequest request) {
        UserResponse res = adminService.createUser(request);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/user/list")
    public ResponseEntity<List<UserResponse>> readUserList() {
        List<UserResponse> res = adminService.readUserList();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/user")
    public ResponseEntity<UserResponse> readUser(@RequestParam String employeeNumber) {
        UserResponse res = adminService.readUserByEmployeeNumber(employeeNumber);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/user")
    public ResponseEntity<Boolean> deleteUser(@RequestParam Long userId) {
        Boolean res = adminService.deleteUser(userId);
        return ResponseEntity.ok(res);
    }


    @PostMapping("/department")
    public ResponseEntity<DepartmentResponse> createDepartment(@RequestParam String name) {
        DepartmentResponse res = adminService.createDepartment(name);
        return ResponseEntity.ok(res);
    }


    @GetMapping("/department/list")
    public ResponseEntity<List<DepartmentResponse>> readDepartmentList() {
        List<DepartmentResponse> res = adminService.readDepartmentList();
        return ResponseEntity.ok(res);
    }


    @DeleteMapping("/department")
    public ResponseEntity<Boolean> deleteDepartment(@RequestParam Long departmentId) {
        adminService.deleteDepartment(departmentId);
        return ResponseEntity.ok(true);
    }
}
