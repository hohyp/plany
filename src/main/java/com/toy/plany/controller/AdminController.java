package com.toy.plany.controller;

import com.toy.plany.dto.request.admin.MemberCreateRequest;
import com.toy.plany.dto.response.admin.DepartmentResponse;
import com.toy.plany.dto.response.admin.MemberResponse;
import com.toy.plany.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    public ResponseEntity<MemberResponse> createMember(@RequestBody @Valid MemberCreateRequest request) {
        MemberResponse res = adminService.createUser(request);
        return ResponseEntity.ok(res);
    }

    public ResponseEntity<DepartmentResponse> createDepartment(@RequestParam String departmentName) {
        DepartmentResponse res = adminService.createDepartment(departmentName);
        return ResponseEntity.ok(res);
    }
}
