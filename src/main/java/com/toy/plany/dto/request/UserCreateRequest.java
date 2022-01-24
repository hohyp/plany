package com.toy.plany.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class CreateUserRequest {
    @NotBlank
    private String employeeNum;

    @NotBlank
    private String password;

    @NotBlank
    private String auth;

    @NotBlank
    private String slackUid;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotNull
    private Long departmentId;

    @Builder
    public CreateUserRequest(String employeeNum, String password, String slackUid, String name, String email, Long departmentId) {
        this.employeeNum = employeeNum;
        this.password = password;
        this.slackUid = slackUid;
        this.name = name;
        this.email = email;
        this.departmentId = departmentId;
    }
}
