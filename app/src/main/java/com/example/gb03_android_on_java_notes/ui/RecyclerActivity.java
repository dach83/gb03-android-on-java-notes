package com.example.gb03_android_on_java_notes.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gb03_android_on_java_notes.App;
import com.example.gb03_android_on_java_notes.R;
import com.example.gb03_android_on_java_notes.domain.Note;
import com.example.gb03_android_on_java_notes.domain.NoteRepository;

public class RecyclerActivity extends AppCompatActivity implements NoteViewHolder.Callbacks {

    private static final int EDITOR_REQUEST_CODE = 1;
    private static final String NOTE_POS_IN_LIST_KEY = "note position in recycler";

    private NoteRepository repository;
    private NoteAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        repository = App.get(this).getNoteRepository();
        initView();
    }

    private void initView() {
        initRecycler();
        findViewById(R.id.add_note_button).setOnClickListener(this::onClickAddNoteButton);
    }

    private void initRecycler() {
        RecyclerView noteRecyclerView = findViewById(R.id.note_recycler_view);
        noteRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NoteAdapter(repository.getNotes(), this);
        noteRecyclerView.setAdapter(adapter);
    }

    private void onClickAddNoteButton(View view) {
        Note note = repository.createNote();
        showNoteEditor(note, adapter.getItemCount());
    }

    @Override
    public void onClickItem(Note note, int position) {
        showNoteEditor(note, position);
    }

    @Override
    public boolean onLongClickItem(Note note, int position) {
        if (repository.removeNote(note.getId())) {
            adapter.notifyItemRemoved(position);
            adapter.notifyItemRangeChanged(position, adapter.getItemCount() - position);
            return true;
        }
        return false;
    }

    private void showNoteEditor(Note note, int position) {
        Intent intent = new Intent(this, EditorActivity.class);
        intent.putExtra(EditorActivity.NOTE_ID_EXTRA_KEY, note.getId());
        intent.putExtra(NOTE_POS_IN_LIST_KEY, position);
        startActivityForResult(intent, EDITOR_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDITOR_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            int position = data.getIntExtra(NOTE_POS_IN_LIST_KEY, adapter.getItemCount());
            adapter.notifyItemChanged(position);
        }
    }
}