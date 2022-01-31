package com.example.gb03_android_on_java_notes.data;

import android.content.Context;

import com.example.gb03_android_on_java_notes.domain.Note;
import com.example.gb03_android_on_java_notes.domain.NoteRepository;
import com.google.gson.Gson;
import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappydbException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SnappyNoteRepositoryImpl implements NoteRepository {

    private static final String NOTE_KEY_PREFIX = "Note2:";
    private DB db = null;
    private final Gson gson = new Gson();

    public void init(Context context) {
        try {
            db = DBFactory.open(context);
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        try {
            db.close();
            db.destroy();
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }

    private String noteKey(UUID noteId) {
        return NOTE_KEY_PREFIX + noteId;
    }

    @Override
    public List<Note> getNotes() {
        List<Note> noteList = new ArrayList<>();
        try {
            String[] keys = db.findKeys(NOTE_KEY_PREFIX);
            for (String key : keys) {
                String json = db.get(key);
                Note note = gson.fromJson(json, Note.class);
                noteList.add(note);
            }
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
        return noteList;
    }

    @Override
    public Note findNote(UUID noteId) {
        Note note = null;
        try {
            String json = db.get(noteKey(noteId));
            note = gson.fromJson(json, Note.class);
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
        return note;
    }

    @Override
    public Note createNote() {
        Note note = new Note();
        updateNote(note);
        return note;
    }

    @Override
    public boolean updateNote(Note note) {
        try {
            String json = gson.toJson(note);
            db.put(noteKey(note.getId()), json);
            return true;
        } catch (SnappydbException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeNote(UUID noteId) {
        try {
            db.del(noteKey(noteId));
            return true;
        } catch (SnappydbException e) {
            e.printStackTrace();
            return false;
        }
    }
}
