package com.toy.plany.service;

import com.toy.plany.dto.request.admin.MemberCreateRequest;
import com.toy.plany.dto.response.admin.DepartmentResponse;
import com.toy.plany.dto.response.admin.MemberResponse;

public interface AdminService {
    MemberResponse createUser(MemberCreateRequest request);

    DepartmentResponse createDepartment(String name);

}
