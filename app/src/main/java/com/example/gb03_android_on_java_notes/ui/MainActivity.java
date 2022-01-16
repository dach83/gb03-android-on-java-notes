package com.example.gb03_android_on_java_notes.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.gb03_android_on_java_notes.R;
import com.example.gb03_android_on_java_notes.domain.Note;
import com.example.gb03_android_on_java_notes.ui.list.ListFragment;

public class MainActivity extends AppCompatActivity implements ListFragment.Controller {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, ListFragment.getInstance())
                    .commit();
        }
    }

    @Override
    public void showNoteEditor(Note note) {
        Toast.makeText(this, "showNoteEditor", Toast.LENGTH_SHORT).show();
    }
}