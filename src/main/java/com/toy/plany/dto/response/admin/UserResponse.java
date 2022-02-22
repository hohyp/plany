package com.toy.plany.dto.response.admin;

import com.toy.plany.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class UserResponse {
    @NotNull
    private Long userId;

    @NotBlank
    private String employeeNum;

    private String slackUid;

    @NotBlank
    private String userName;

    @NotNull
    private Long departmentId;

    @NotNull
    private String department;

    @NotBlank
    private String color;

    @NotBlank
    private String fontColor;

    private String note;

    @Builder
    public UserResponse(Long userId, String employeeNum, String slackUid, String userName, Long departmentId, String department, String color, String fontColor, String note) {
        this.userId = userId;
        this.employeeNum = employeeNum;
        this.slackUid = slackUid;
        this.userName = userName;
        this.departmentId = departmentId;
        this.department = department;
        this.color = color;
        this.fontColor = fontColor;
        this.note = note;
    }

    static public UserResponse from(User user) {
        if (user == null)
            return null;

        return UserResponse.builder()
                .userId(user.getId())
                .employeeNum(user.getEmployeeNum())
                .userName(user.getName())
                .color(user.getColor().getColor().getCode())
                .fontColor(user.getColor().getColor().getFontColor().getCode())
                .department(user.getDepartment().getName())
                .departmentId(user.getDepartment().getId())
                .slackUid(user.getSlackUid())
                .note(user.getNote())
                .build();
    }
}
