package com.toy.plany.dto.response.event;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class EventUserResponse {
    @NotNull
    private Long id;

    @NotBlank
    private String employeeNum;

    @NotBlank
    private String name;

    @NotNull
    private String department;

    @NotBlank
    private String color;

    @NotBlank
    private String fontColor;

    private String position;

    @Builder
    public EventUserResponse(Long id, String employeeNum, String name, String department, String color, String fontColor, String position) {
        this.id = id;
        this.employeeNum = employeeNum;
        this.name = name;
        this.department = department;
        this.color = color;
        this.fontColor = fontColor;
        this.position = position;
    }
}
