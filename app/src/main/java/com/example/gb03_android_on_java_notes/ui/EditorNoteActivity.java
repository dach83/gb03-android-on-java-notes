package com.example.gb03_android_on_java_notes.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.gb03_android_on_java_notes.App;
import com.example.gb03_android_on_java_notes.R;
import com.example.gb03_android_on_java_notes.domain.NoteEntity;
import com.example.gb03_android_on_java_notes.domain.NoteRepository;
import com.google.android.material.textfield.TextInputEditText;

public class EditorNoteActivity extends AppCompatActivity {

    public static final String NOTE_ID_EXTRA_KEY = "note_id_extra_key";

    private NoteEntity note;
    private NoteRepository noteRepository;

    private TextView dateTextView;
    private TextInputEditText headerEditText;
    private TextInputEditText contentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_note);

        noteRepository = App.get(this).getNoteRepository();

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(NOTE_ID_EXTRA_KEY)) {
            int noteId = intent.getIntExtra(NOTE_ID_EXTRA_KEY, -1);
            note = noteRepository.findNote(noteId);
        }

        initView();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        updateNote();
    }

    private void initView() {
        dateTextView = findViewById(R.id.date_text_view);
        headerEditText = findViewById(R.id.header_edit_text);
        contentEditText = findViewById(R.id.content_edit_text);

        if (note != null) {
            dateTextView.setText(NoteUtils.dateToString(note.getDate()));
            headerEditText.setText(note.getHeader());
            contentEditText.setText(note.getContent());
        }
    }

    private void updateNote() {
        note.setHeader(headerEditText.getText().toString());
        note.setContent(contentEditText.getText().toString());
        noteRepository.updateNote(note);
    }
}