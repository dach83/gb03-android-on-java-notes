package com.example.gb03_android_on_java_notes.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.gb03_android_on_java_notes.R;
import com.example.gb03_android_on_java_notes.data.SampleNoteRepository;
import com.example.gb03_android_on_java_notes.domain.NoteRepository;

public class NoteListActivity extends AppCompatActivity {

    private NoteRepository noteRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        noteRepository = new SampleNoteRepository();
        initNoteRecyclerView();
    }

    private void initNoteRecyclerView() {
        RecyclerView noteRecyclerView = findViewById(R.id.note_recycler_view);
        noteRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        NoteAdapter adapter = new NoteAdapter(noteRepository.getNotes());
        noteRecyclerView.setAdapter(adapter);
    }
}