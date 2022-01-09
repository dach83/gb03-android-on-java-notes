package com.example.gb03_android_on_java_notes.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gb03_android_on_java_notes.R;
import com.example.gb03_android_on_java_notes.data.SampleNoteRepository;
import com.example.gb03_android_on_java_notes.domain.NoteEntity;
import com.example.gb03_android_on_java_notes.domain.NoteRepository;

public class ListNoteActivity extends AppCompatActivity implements NoteViewHolder.Callbacks {

    private NoteRepository noteRepository;
    private NoteAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_note);

        noteRepository = new SampleNoteRepository();
        initView();
    }

    private void initView() {
        initRecyclerView();
        findViewById(R.id.add_note_button).setOnClickListener(this::onClickAddNoteButton);
    }

    private void initRecyclerView() {
        RecyclerView noteRecyclerView = findViewById(R.id.note_recycler_view);
        noteRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NoteAdapter(noteRepository.getNotes(), this);
        noteRecyclerView.setAdapter(adapter);
    }

    private void onClickAddNoteButton(View view) {
        NoteEntity note = noteRepository.createNote();
        showNoteEditor(note);
        adapter.notifyDataSetChanged(); // неизвестно в какой позиции окажется новая заметка
    }

    @Override
    public void onNoteSelected(int noteId, int position) {
        NoteEntity note = noteRepository.findNote(noteId);
        if (note != null) {
            showNoteEditor(note);
            adapter.notifyItemChanged(position);
        }
    }

    @Override
    public boolean onNoteRemove(int noteId, int position) {
        if (noteRepository.removeNote(noteId)) {
            adapter.notifyItemRemoved(position);
            return true;
        } else {
            return false;
        }
    }

    private void showNoteEditor(NoteEntity note) {
        Intent intent = new Intent(this, DetailNoteActivity.class);
        intent.putExtra(DetailNoteActivity.NOTE_ID_EXTRA_KEY, note.getId());
        startActivity(intent);
    }
}