package com.toy.plany.dto.request.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class UpdatePasswordRequest {
    @NotBlank
    @NotNull
    private String currentPassword;

    @NotBlank
    @NotNull
    private String newPassword;

    @NotBlank
    @NotNull
    private String validatedPassword;

    @Builder
    public UpdatePasswordRequest(String currentPassword, String newPassword, String validatedPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.validatedPassword = validatedPassword;
    }
}
