package com.example.gb03_android_on_java_notes.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.gb03_android_on_java_notes.R;

public class DetailNoteActivity extends AppCompatActivity {

    public static final String NOTE_ID_EXTRA_KEY = "note_id_extra_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_note);
    }
}