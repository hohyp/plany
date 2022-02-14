package com.toy.plany.entity.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
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
    PINK1("#FFC6E1", FontColor.BLACK),
    PINK2("#FF8CC3", FontColor.WHITE),
    PINK3("#FF4AA1", FontColor.WHITE),
    PINK4("#FF007A", FontColor.WHITE),

    //VIOLET
    VIOLET1("#F4AFFF", FontColor.BLACK),
    VIOLET2("#EB6EFF", FontColor.WHITE),
    VIOLET3("#BD25DB", FontColor.WHITE),
    VIOLET4("#9300AB", FontColor.WHITE),
    VIOLET5("#5D006C", FontColor.WHITE),

    VIOLET6("#D4B1FF", FontColor.BLACK),
    VIOLET7("#BF8CFF", FontColor.WHITE),
    VIOLET8("#A661FF", FontColor.WHITE),
    VIOLET9("#951DF2", FontColor.WHITE),

    VIOLET10("#BAB5FF", FontColor.BLACK),
    VIOLET11("#948BFF", FontColor.WHITE),
    VIOLET12("#796EFF", FontColor.WHITE),
    VIOLET13("#571CE8", FontColor.WHITE),

    //BLUE
    BLUE1("#759CFF", FontColor.WHITE),
    BLUE2("#376FFF", FontColor.WHITE),
    BLUE3("#122AFF", FontColor.WHITE),
    BLUE4("#00138F", FontColor.WHITE),

    SKY_BLUE1("#9CE7FF", FontColor.BLACK),
    SKY_BLUE2("#19E4FF", FontColor.BLACK),
    SKY_BLUE3("#00C2FF", FontColor.BLACK),
    SKY_BLUE4("#00A3FF", FontColor.BLACK),
    SKY_BLUE5("#00A3FF", FontColor.BLACK),

    //GREEN
    GREEN1("#CDFFEE", FontColor.BLACK),
    GREEN2("#7FFFD4", FontColor.BLACK),
    GREEN3("#02FFAA", FontColor.BLACK),
    GREEN4("#00FF38", FontColor.BLACK),
    GREEN5("#DCFFA3", FontColor.BLACK),
    GREEN6("#BFFF57", FontColor.BLACK),
    GREEN7("#90E900", FontColor.BLACK),
    GREEN8("#42CE00", FontColor.BLACK),


    //GRAY
    GRAY1("#D6D6D6", FontColor.BLACK),
    GRAY2("#A8A8A8", FontColor.BLACK),
    GRAY3("#818181", FontColor.WHITE),
    GRAY4("#5C5C5C", FontColor.WHITE);

    private String code;
    private FontColor fontColor;
    private Boolean isUsed;
    static private Integer colorCount;

    Color(String code, FontColor fontColor) {
        this.code = code;
        this.fontColor = fontColor;
        this.isUsed = false;
    }

    public void use(Color color) {
        colorCount++;
        color.isUsed = true;
    }

    //TODO used false 인것만 리스트 생성하기

    public static Color getRandomColor() {
        if(colorCount >= 50)
            return null;
        List<Color> VALUES = List.of(values());
        final int SIZE = VALUES.size();
        Random RANDOM = new Random();
        Color color = VALUES.get(RANDOM.nextInt(SIZE));
        color.use(color);
        return color;
    }
}
