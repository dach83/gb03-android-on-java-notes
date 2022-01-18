package com.example.gb03_android_on_java_notes.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.gb03_android_on_java_notes.R;
import com.example.gb03_android_on_java_notes.domain.Note;
import com.example.gb03_android_on_java_notes.ui.editor.EditorFragment;
import com.example.gb03_android_on_java_notes.ui.list.ListFragment;

public class MainActivity extends AppCompatActivity implements ListFragment.Controller, EditorFragment.Controller {

    private static final String EDITOR_BACK_STACK_NAME = "editor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.list_fragment_container, ListFragment.getInstance())
                    .commit();
        }
    }

    @Override
    public void onNoteSelected(Note note) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Если в альбомном режиме переключаться между разными заметками, то
        // в стек добавляются несколько фрагментов редактора заметок. Как следствие,
        // кнопка "назад" будет приводить не к списку заметок, а к редактору предыдущей заметки.
        // Поэтому предварительно удаляю из стека предыдущий фрагмент с редактором.
        fragmentManager
                .popBackStack(EDITOR_BACK_STACK_NAME, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        fragmentManager
                .beginTransaction()
                .replace(R.id.editor_fragment_container, EditorFragment.getInstance(note))
                .addToBackStack(EDITOR_BACK_STACK_NAME)
                .commit();
    }

    @Override
    public void onNoteChanged(Note note) {
        ListFragment listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.list_fragment_container);
        if (listFragment != null) {
            listFragment.notifyNoteChanged(note);
        }
    }
}