package com.example.gb03_android_on_java_notes.ui;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gb03_android_on_java_notes.R;
import com.example.gb03_android_on_java_notes.domain.Color;
import com.example.gb03_android_on_java_notes.domain.NoteEntity;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class NoteViewHolder extends RecyclerView.ViewHolder {

    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy", Locale.US);

    private final View circleView = itemView.findViewById(R.id.circle_view);
    private final TextView headerTextView = itemView.findViewById(R.id.header_text_view);
    private final TextView contentTextView = itemView.findViewById(R.id.content_text_view);
    private final TextView dateTextView = itemView.findViewById(R.id.date_text_view);

    public NoteViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void bind(NoteEntity note) {
        fillCircleBackgroundColor(note.getColor());
        headerTextView.setText(note.getHeader());
        contentTextView.setText(note.getContent());
        dateTextView.setText(dateFormatter.format(note.getDate()));
    }

    private void fillCircleBackgroundColor(Color color) {
        int rgb = itemView.getResources().getColor(color.getId(), null);
        circleView.getBackground().setTint(rgb);
    }
}
