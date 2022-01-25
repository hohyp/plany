package com.toy.plany.dto.request.admin;

import com.toy.plany.entity.enums.MemberPosition;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class MemberCreateRequest {
    @NotBlank
    private String employeeNum;

    @NotBlank
    private String password;

    @NotBlank
    private String slackUid;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotNull
    private Long departmentId;

    @NotNull
    private MemberPosition position;

    @Builder
    public MemberCreateRequest(String employeeNum, String password, String slackUid, String name, String email, Long departmentId, MemberPosition position) {
        this.employeeNum = employeeNum;
        this.password = password;
        this.slackUid = slackUid;
        this.name = name;
        this.email = email;
        this.departmentId = departmentId;
        this.position = position;
    }
}
