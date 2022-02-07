package com.toy.plany.dto.request.admin;

import com.toy.plany.entity.enums.UserPosition;
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
    private String name;

    @NotBlank
    private String email;

    @NotNull
    private Long departmentId;

    @NotNull
    private String position;

    @Builder
    public UserCreateRequest(String employeeNum, String name, String email, Long departmentId, String position) {
        this.employeeNum = employeeNum;
        this.name = name;
        this.email = email;
        this.departmentId = departmentId;
        this.position = position;
    }
}
