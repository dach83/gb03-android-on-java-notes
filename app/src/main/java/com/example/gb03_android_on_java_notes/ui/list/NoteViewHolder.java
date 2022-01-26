package com.example.gb03_android_on_java_notes.ui.list;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gb03_android_on_java_notes.R;
import com.example.gb03_android_on_java_notes.domain.Color;
import com.example.gb03_android_on_java_notes.domain.Note;
import com.example.gb03_android_on_java_notes.utils.NoteUtils;

public class NoteViewHolder extends RecyclerView.ViewHolder {

    private Callbacks callbacks;
    private final View circleView = itemView.findViewById(R.id.color_circle_view);
    private final TextView headerTextView = itemView.findViewById(R.id.header_text_view);
    private final TextView contentTextView = itemView.findViewById(R.id.content_text_view);
    private final TextView dateTextView = itemView.findViewById(R.id.date_text_view);

    public NoteViewHolder(@NonNull View itemView, Callbacks callbacks) {
        super(itemView);
        this.callbacks = callbacks;
    }

    public void bind(Note note, int position) {
        fillCircleBackgroundTintColor(note.getColor());
        headerTextView.setText(note.getHeader());
        contentTextView.setText(note.getContent());
        dateTextView.setText(NoteUtils.dateToString(note.getDate()));

        if (callbacks != null) {
            itemView.setOnClickListener(view -> callbacks.onClickItem(note));
            itemView.setOnLongClickListener(view -> callbacks.onLongClickItem(note, itemView));
            callbacks.onBindItem(note, position);
        }
    }

    private void fillCircleBackgroundTintColor(Color color) {
        int rgb = itemView.getResources().getColor(color.getColorId(), null);
        circleView.getBackground().setTint(rgb);
    }

    interface Callbacks {
        void onBindItem(Note note, int position);

        void onClickItem(Note note);

        boolean onLongClickItem(Note note, View item);
    }
}
