package com.toy.plany.dto.response.admin;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
public class DepartmentResponse {
    private Long id;
    private String name;
}
