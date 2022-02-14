package com.toy.plany.dto.response.event;

import com.toy.plany.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class AttendantResponse {
    @NotNull
    private Long userId;

    @NotBlank
    private String employeeNum;

    @NotBlank
    private String userName;

    @NotNull
    private String department;

    @NotBlank
    private String color;

    @NotBlank
    private String fontColor;

    private String position;

    @Builder
    public AttendantResponse(Long userId, String employeeNum, String userName, String department, String color, String fontColor, String position) {
        this.userId = userId;
        this.employeeNum = employeeNum;
        this.userName = userName;
        this.department = department;
        this.color = color;
        this.fontColor = fontColor;
        this.position = position;
    }

    public static AttendantResponse from(User user){
        return AttendantResponse.builder()
                .userId(user.getId())
                .employeeNum(user.getEmployeeNum())
                .userName(user.getName())
                .color(user.getColor().getCode())
                .fontColor(user.getColor().getFontColor().getCode())
                .department(user.getDepartment().getName())
                .build();
    }
}
