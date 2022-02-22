package com.toy.plany.service;

import com.toy.plany.dto.request.user.LoginRequest;
import com.toy.plany.dto.request.user.UpdatePasswordRequest;
import com.toy.plany.dto.response.admin.UserResponse;
import com.toy.plany.dto.response.auth.LoginResponse;

import java.util.List;

public interface UserService {
    LoginResponse login(LoginRequest request);

    UserResponse insertSlackUid(Long userId, String slackUid);

    UserResponse updatePassword(Long userId, UpdatePasswordRequest request);

    List<UserResponse> readMyDepartmentUser(Long departmentId);

    List<UserResponse> readAutoCompleteUserList(String keyword);
}
