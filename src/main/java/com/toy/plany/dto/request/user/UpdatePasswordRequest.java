package com.toy.plany.dto.request.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class UpdatePasswordRequest {
    @NotBlank
    private String password;

    @Builder
    public UpdatePasswordRequest(String password) {
        this.password = password;
    }
}
