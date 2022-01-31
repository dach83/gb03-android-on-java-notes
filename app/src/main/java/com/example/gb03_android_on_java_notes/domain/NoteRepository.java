package com.example.gb03_android_on_java_notes.domain;

import java.util.List;
import java.util.UUID;

public interface NoteRepository {

    List<Note> getNotes();

    Note findNote(UUID noteId);

    Note createNote();

    boolean updateNote(Note note);

    boolean removeNote(UUID noteId);

}
