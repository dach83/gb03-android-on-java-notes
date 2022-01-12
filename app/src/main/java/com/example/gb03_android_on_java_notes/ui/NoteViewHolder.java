package com.example.gb03_android_on_java_notes.ui;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gb03_android_on_java_notes.R;
import com.example.gb03_android_on_java_notes.domain.Color;
import com.example.gb03_android_on_java_notes.domain.Note;

public class NoteViewHolder extends RecyclerView.ViewHolder {

    interface Callbacks {
        void onClickItem(Note note, int position);
        boolean onLongClickItem(Note note, int position);
    }

    private final Callbacks callbacks;
    private final View circleView = itemView.findViewById(R.id.circle_view);
    private final TextView headerTextView = itemView.findViewById(R.id.header_text_view);
    private final TextView contentTextView = itemView.findViewById(R.id.content_text_view);
    private final TextView dateTextView = itemView.findViewById(R.id.date_text_view);

    public NoteViewHolder(@NonNull View itemView, Callbacks callbacks) {
        super(itemView);
        this.callbacks = callbacks;
    }

    public void bind(Note note, int position) {
        fillCircleBackgroundColor(note.getColor());
        headerTextView.setText(note.getHeader());
        contentTextView.setText(note.getContent());
        dateTextView.setText(NoteUtils.dateToString(note.getDate()));

        if (callbacks != null) {
            itemView.setOnClickListener(view -> callbacks.onClickItem(note, position));
            itemView.setOnLongClickListener(view -> callbacks.onLongClickItem(note, position));
        }
    }

    private void fillCircleBackgroundColor(Color color) {
        int rgb = itemView.getResources().getColor(color.getId(), null);
        circleView.getBackground().setTint(rgb);
    }
}
