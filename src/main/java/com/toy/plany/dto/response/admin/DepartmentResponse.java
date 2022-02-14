package com.toy.plany.dto.response.admin;

import com.toy.plany.entity.Department;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DepartmentResponse {
    private Long departmentId;
    private String departmentName;

    @Builder
    public DepartmentResponse(Long departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    static public DepartmentResponse from(Department department){
        return DepartmentResponse.builder()
                .departmentId(department.getId())
                .departmentName(department.getName())
                .build();
    }
}
