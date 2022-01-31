package com.example.gb03_android_on_java_notes.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.gb03_android_on_java_notes.R;

public class AppStartCounterActivity extends AppCompatActivity {

    private static final String COUNTER_EXTRA_KEY = "COUNTER_EXTRA_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_start_counter);
        int counter = getIntent().getIntExtra(COUNTER_EXTRA_KEY, 0);
        TextView counterTextView = findViewById(R.id.counter_text_view);
        counterTextView.setText("Start counter: " + counter);
    }

    public static Intent newIntent(Context context, int counter) {
        Intent intent = new Intent(context, AppStartCounterActivity.class);
        intent.putExtra(COUNTER_EXTRA_KEY, counter);
        return intent;
    }
}