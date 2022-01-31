package com.example.gb03_android_on_java_notes.domain;

import com.example.gb03_android_on_java_notes.R;

import java.io.Serializable;

public enum Color implements Serializable {
    FIRE_BRICK(R.color.fire_brick),
    ORANGE_RED(R.color.orange_red),
    GOLD(R.color.gold),
    SEA_GREEN(R.color.sea_green),
    SLATE_BLUE(R.color.slate_blue),
    STEEL_BLUE(R.color.steel_blue),
    CORNFLOWER_BLUE(R.color.cornflower_blue),
    MEDIUM_AQUAMARINE(R.color.medium_aquamarine),
    DARK_MAGENTA(R.color.dark_magenta),
    INDIAN_RED(R.color.indian_red),
    DIM_GRAY(R.color.dim_gray);

    private final int colorId;

    Color(int colorId) {
        this.colorId = colorId;
    }

    public int getColorId() {
        return colorId;
    }

    public static Color randomColor() {
        int randomInd = (int) (Color.values().length * Math.random());
        return Color.values()[randomInd];
    }
}
