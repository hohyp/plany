package com.toy.plany.entity.enums;

import lombok.Getter;

import java.util.function.Function;

@Getter
public enum Color {

    //YELLOW
    LIGHT_YELLOW("#FFF7B8", FontColor.BLACK),
    LEMON_YELLOW("#FFEE72", FontColor.BLACK),
    PERMANENT_YELLOW("#FFE101", FontColor.BLACK),
    COBALT_YELLOW("#FFC701", FontColor.BLACK),

    //ORANGE
    ORANGE1("#FFC9AA", FontColor.BLACK),
    ORANGE2("#FFA26E", FontColor.WHITE),
    ORANGE3("#FF7A00", FontColor.WHITE),
    ORANGE4("#FF5C00", FontColor.WHITE),

    //RED
    RED("#FFB2B2", FontColor.BLACK),
    RED1("#FF7E7E", FontColor.WHITE),
    RED2("#FF5959", FontColor.WHITE),
    PERMANENT_RED("#FF0000", FontColor.WHITE),

    //PINK
    PINK1("", FontColor.BLACK),
    PINK2("", FontColor.WHITE),
    PINK3("", FontColor.WHITE),
    PINK4("", FontColor.WHITE),

    //VIOLET
    VIOLET1("", FontColor.BLACK),
    VIOLET2("", FontColor.WHITE),
    VIOLET3("", FontColor.WHITE),
    VIOLET4("", FontColor.WHITE),
    VIOLET5("", FontColor.WHITE),

    VIOLET6("", FontColor.BLACK),
    VIOLET7("", FontColor.WHITE),
    VIOLET8("", FontColor.WHITE),
    VIOLET9("", FontColor.WHITE),

    VIOLET10("", FontColor.BLACK),
    VIOLET11("", FontColor.WHITE),
    VIOLET12("", FontColor.WHITE),
    VIOLET13("", FontColor.WHITE),

    //BLUE

    //GREEN

    //GRAY
    ;
    private String code;
    private FontColor fontColor;
    private Boolean isUsed;

    Color(String code, FontColor fontColor) {
        this.code = code;
        this.fontColor = fontColor;
        this.isUsed = false;
    }

    public void use(Color color) {
        color.isUsed = true;
    }
}
