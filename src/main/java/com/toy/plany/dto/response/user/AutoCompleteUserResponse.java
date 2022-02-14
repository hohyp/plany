package com.toy.plany.dto.response.user;

import com.toy.plany.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AutoCompleteUserResponse {
    private Long userId;
    private String name;

    @Builder
    private AutoCompleteUserResponse(Long userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    static public AutoCompleteUserResponse from(User user){
        return AutoCompleteUserResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .build();
    }
}
