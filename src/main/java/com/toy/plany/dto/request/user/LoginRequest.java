package com.toy.plany.dto.request.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class LoginRequest {
    @NotBlank
    private String employeeNumber;

    @NotBlank
    private String password;

    @Builder
    public LoginRequest(String employeeNumber, String password) {
        this.employeeNumber = employeeNumber;
        this.password = password;
    }
}
