package com.example.gb03_android_on_java_notes;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.gb03_android_on_java_notes.data.MemoryNoteRepositoryImpl;
import com.example.gb03_android_on_java_notes.data.SnappyNoteRepositoryImpl;
import com.example.gb03_android_on_java_notes.domain.NoteRepository;

public class App extends Application {

    private static final String SHARED_PREFERENCES_NAME = "com.example.gb03_android_on_java_notes";

    private static App instance;
    private SnappyNoteRepositoryImpl noteRepository = new SnappyNoteRepositoryImpl();

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        noteRepository.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        noteRepository.destroy();
    }

    public static App get() {
        return instance;
    }

    public NoteRepository getNoteRepository() {
        return noteRepository;
    }

    public SharedPreferences getNoteSharedPreferences() {
        return getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);
    }
}
