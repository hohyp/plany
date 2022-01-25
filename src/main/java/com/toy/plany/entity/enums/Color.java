package com.toy.plany.entity.enums;

import lombok.Getter;

@Getter
public enum Color {
    RED("#ffeeff", false),
    BLUE("#ttcctt", false);

    private String code;
    private Boolean isUsed;

    Color(String code, Boolean isUsed) {
        this.code = code;
        this.isUsed = false;
    }

    public void use(Color color){
        color.isUsed = true;
    }
}
