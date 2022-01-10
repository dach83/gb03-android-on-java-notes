package com.example.gb03_android_on_java_notes.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gb03_android_on_java_notes.R;
import com.example.gb03_android_on_java_notes.domain.NoteEntity;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {

    interface Callbacks {
        void onClickNoteItem(int noteId, int position);

        boolean onLongClickNoteItem(int noteId, int position);
    }

    private final Callbacks callbacks;
    private final List<NoteEntity> notes;

    public NoteAdapter(List<NoteEntity> notes, Callbacks callbacks) {
        this.notes = notes;
        this.callbacks = callbacks;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        NoteEntity note = notes.get(position);
        holder.bind(note);
        if (callbacks != null) {
            holder.itemView.setOnClickListener(view -> callbacks.onClickNoteItem(note.getId(), position));
            holder.itemView.setOnLongClickListener(view -> callbacks.onLongClickNoteItem(note.getId(), position));
        }
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

}
