package com.toy.plany.service;

import com.toy.plany.dto.request.admin.UserCreateRequest;
import com.toy.plany.dto.response.admin.DepartmentResponse;
import com.toy.plany.dto.response.admin.UserResponse;

import java.util.List;

public interface AdminService {
    UserResponse createUser(UserCreateRequest request);

    UserResponse readUserByEmployeeNumber(String employeeNumber);

    List<UserResponse> readUserList();

    Boolean deleteUser(Long userId);

    Boolean deleteUser(List<Long> userIdList);

    DepartmentResponse createDepartment(String name);

    List<DepartmentResponse> readDepartmentList();

    Boolean deleteDepartment(Long departmentId);
}
