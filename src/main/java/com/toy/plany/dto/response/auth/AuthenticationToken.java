package com.toy.plany.dto.response.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Getter
@NoArgsConstructor
public class AuthenticationToken {
    private String employeeNum;
    private Collection authorities;
    private String token;

    @Builder
    public AuthenticationToken(String employeeNum, Collection authorities, String token) {
        this.employeeNum = employeeNum;
        this.authorities = authorities;
        this.token = token;
    }
}
