package com.toy.plany.dto.request.admin;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class UserCreateRequest {
    @NotBlank
    private String employeeNum;

    @NotBlank
    private String userName;

    @NotNull
    private Long departmentId;

    private String note;

    @Builder
    public UserCreateRequest(String employeeNum, String userName, Long departmentId, String note) {
        this.employeeNum = employeeNum;
        this.userName = userName;
        this.departmentId = departmentId;
        this.note = note;
    }
}
