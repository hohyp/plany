package com.toy.plany.entity.enums;

import lombok.Getter;

@Getter
public enum FontColor {

    BLACK("#353535"),
    WHITE("#FFFFFF");

    private String code;

    FontColor(String code) {
        this.code = code;
    }


}
