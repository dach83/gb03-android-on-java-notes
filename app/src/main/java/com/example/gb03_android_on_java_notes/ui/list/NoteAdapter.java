package com.example.gb03_android_on_java_notes.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gb03_android_on_java_notes.R;
import com.example.gb03_android_on_java_notes.domain.Note;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {

    private final NoteViewHolder.Callbacks callbacks;
    private List<Note> notes;

    public NoteAdapter(List<Note> notes, NoteViewHolder.Callbacks callbacks) {
        this.notes = notes;
        this.callbacks = callbacks;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(itemView, callbacks);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.bind(notes.get(position), position);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}
