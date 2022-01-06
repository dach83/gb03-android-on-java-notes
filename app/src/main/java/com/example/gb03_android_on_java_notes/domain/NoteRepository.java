package com.example.gb03_android_on_java_notes.domain;

import java.util.List;

public interface NoteRepository {

    List<NoteEntity> getNotes();

    void removeNote(NoteEntity note);

}
