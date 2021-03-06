package com.toy.plany.service;

import com.toy.plany.dto.request.admin.UserCreateRequest;
import com.toy.plany.dto.request.admin.UserUpdateRequest;
import com.toy.plany.dto.response.admin.DepartmentResponse;
import com.toy.plany.dto.response.admin.UserResponse;
import com.toy.plany.entity.Color;
import com.toy.plany.entity.enums.Colors;

import java.util.List;

public interface AdminService {
    UserResponse createUser(UserCreateRequest request);

    UserResponse readUserByEmployeeNumber(String employeeNumber);

    List<UserResponse> readUserByName(String name);

    List<UserResponse> readUserList();

    Boolean deleteUser(Long userId);

    Boolean deleteUser(List<Long> userIdList);

    DepartmentResponse createDepartment(String name);

    List<DepartmentResponse> readDepartmentList();

    UserResponse updateUser(Long userId, UserUpdateRequest request);

    Boolean deleteDepartment(Long departmentId);

    Color addColor(Colors colors);
}
