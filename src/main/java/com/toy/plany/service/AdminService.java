package com.toy.plany.service;

import com.toy.plany.dto.request.admin.UserCreateRequest;
import com.toy.plany.dto.response.admin.DepartmentResponse;
import com.toy.plany.dto.response.admin.UserResponse;

import java.util.List;

public interface AdminService {
    UserResponse createUser(UserCreateRequest request);

    UserResponse readUserByEmployeeNumber(String employeeNumber);

    List<UserResponse> readUserList();

    List<UserResponse> deleteUser(Long userId);

    DepartmentResponse createDepartment(String name);

    List<DepartmentResponse> readDepartmentList();

    List<DepartmentResponse> deleteDepartment(Long departmentId);

}
