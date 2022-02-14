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

    @NotNull
    private Long departmentId;


    @Builder
    public UserCreateRequest(String employeeNum, String name, Long departmentId) {
        this.employeeNum = employeeNum;
        this.name = name;
        this.departmentId = departmentId;
    }
}
