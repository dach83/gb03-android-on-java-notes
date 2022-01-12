package com.example.gb03_android_on_java_notes;

import android.app.Application;
import android.content.Context;

import com.example.gb03_android_on_java_notes.data.MemoryNoteRepository;
import com.example.gb03_android_on_java_notes.domain.NoteRepository;

public class App extends Application {

    private NoteRepository noteRepository = new MemoryNoteRepository();

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    public NoteRepository getNoteRepository() {
        return noteRepository;
    }
}
