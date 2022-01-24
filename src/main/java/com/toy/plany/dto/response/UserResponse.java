package com.toy.plany.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class CreateUserResponse{
    @NotNull
    private Long id;

    @NotBlank
    private String employeeNum;

    @NotBlank
    private String slackUid;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotNull
    private Long departmentId;

    @NotBlank
    private String color;

    @Builder
    public CreateUserResponse(Long id, String employeeNum, String slackUid, String name, String email, Long departmentId, String color) {
        this.id = id;
        this.employeeNum = employeeNum;
        this.slackUid = slackUid;
        this.name = name;
        this.email = email;
        this.departmentId = departmentId;
        this.color = color;
    }
}
