package com.example.gb03_android_on_java_notes.domain;

import java.util.List;

public interface NoteRepository {

    List<NoteEntity> getNotes();

    NoteEntity createNote();

    NoteEntity findNote(int noteId);

    boolean removeNote(int noteId);

}
