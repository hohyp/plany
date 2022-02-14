package com.toy.plany.dto.response.admin;

import com.toy.plany.dto.dtos.AuthorityDto;
import com.toy.plany.entity.User;
import com.toy.plany.entity.enums.Color;
import com.toy.plany.entity.enums.FontColor;
import com.toy.plany.entity.enums.UserPosition;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class UserResponse {
    @NotNull
    private Long id;

    @NotBlank
    private String employeeNum;

    private String slackUid;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotNull
    private String department;

    @NotBlank
    private String color;

    @NotBlank
    private String fontColor;

    private String position;

    @Builder
    public UserResponse(Long id, String employeeNum, String slackUid, String name, String email, String department, String color, String fontColor, String position) {
        this.id = id;
        this.employeeNum = employeeNum;
        this.slackUid = slackUid;
        this.name = name;
        this.email = email;
        this.department = department;
        this.color = color;
        this.fontColor = fontColor;
        this.position = position;
    }

    static public UserResponse from(User user) {
        if (user == null)
            return null;

        return UserResponse.builder()
                .id(user.getId())
                .employeeNum(user.getEmployeeNum())
                .name(user.getName())
                .color(user.getColor().getCode())
                .fontColor(user.getColor().getFontColor().getCode())
                .department(user.getDepartment().getName())
                .slackUid(user.getSlackUid())
                .build();
    }
}
