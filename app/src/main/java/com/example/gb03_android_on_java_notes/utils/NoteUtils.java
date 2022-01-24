package com.example.gb03_android_on_java_notes.utils;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

import androidx.appcompat.content.res.AppCompatResources;

import com.example.gb03_android_on_java_notes.R;
import com.example.gb03_android_on_java_notes.domain.Color;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NoteUtils {

    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy", Locale.US);

    public static String dateToString(Date date) {
        return dateFormatter.format(date);
    }

    public static Drawable colorCircleDrawable(Context context, Color color) {
        int rgb = context.getResources().getColor(color.getColorId(), null);
        Drawable colorCircle = AppCompatResources.getDrawable(context, R.drawable.bg_circle);
        colorCircle.setColorFilter(rgb, PorterDuff.Mode.SRC_IN);
        return colorCircle;
    }
}
