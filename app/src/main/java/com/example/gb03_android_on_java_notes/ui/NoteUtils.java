package com.example.gb03_android_on_java_notes.ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NoteUtils {

    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy", Locale.US);

    public static String dateToString(Date date) {
        return dateFormatter.format(date);
    }

}
