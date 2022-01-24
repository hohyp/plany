package com.toy.plany.dto.response.member;

import com.toy.plany.entity.enums.MemberPosition;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class MemberResponse {
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
    private String department;

    @NotBlank
    private String color;

    private MemberPosition position;

    @Builder
    public MemberResponse(Long id, String employeeNum, String slackUid, String name, String email, String department, String color, MemberPosition position) {
        this.id = id;
        this.employeeNum = employeeNum;
        this.slackUid = slackUid;
        this.name = name;
        this.email = email;
        this.department = department;
        this.color = color;
        this.position = position;
    }
}
