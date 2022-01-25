package com.toy.plany.dto.request.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuthenticationRequest {
    private String employeeNum;
    private String password;

    @Builder
    public AuthenticationRequest(String employeeNum, String password) {
        this.employeeNum = employeeNum;
        this.password = password;
    }
}
