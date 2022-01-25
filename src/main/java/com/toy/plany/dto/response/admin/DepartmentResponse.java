package com.toy.plany.dto.response.admin;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DepartmentResponse {
    private Long id;
    private String name;

    @Builder
    public DepartmentResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
