package com.example.gb03_android_on_java_notes.ui.editor;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gb03_android_on_java_notes.App;
import com.example.gb03_android_on_java_notes.R;
import com.example.gb03_android_on_java_notes.domain.Color;
import com.example.gb03_android_on_java_notes.domain.Note;
import com.example.gb03_android_on_java_notes.domain.NoteRepository;
import com.example.gb03_android_on_java_notes.utils.NoteUtils;
import com.google.android.material.textfield.TextInputEditText;

import java.util.UUID;

public class EditorFragment extends Fragment {

    public static final String NOTE_ID_KEY = "note_id_key";

    private Note note;
    private NoteRepository repository;

    private MenuItem selectColorMenuItem;
    private TextInputEditText headerEditText;
    private TextInputEditText contentEditText;

    private Context context;
    private Controller controller;

    public static EditorFragment getInstance(Note note) {
        EditorFragment fragment = new EditorFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(NOTE_ID_KEY, note.getId());
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        if (context instanceof Controller) {
            controller = (Controller) context;
        } else {
            throw new IllegalStateException("Activity must implement EditorFragment.Controller");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_editor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        repository = App.get().getNoteRepository();
        UUID noteId = (UUID) getArguments().getSerializable(NOTE_ID_KEY);
        note = repository.findNote(noteId);
        initView(view);
    }

    private void initView(View view) {
        TextView dateTextView = view.findViewById(R.id.date_text_view);
        headerEditText = view.findViewById(R.id.header_edit_text);
        contentEditText = view.findViewById(R.id.content_edit_text);
        if (note != null) {
            dateTextView.setText(NoteUtils.dateToString(note.getDate()));
            headerEditText.setText(note.getHeader());
            contentEditText.setText(note.getContent());
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        if(controller.inPortraitMode()) {
            menu.clear(); // в портретном режиме показываем только меню редактора
        }
        Log.d("@@@", "null() called with: menu = [" + menu + "], inflater = [" + inflater + "]");
        inflater.inflate(R.menu.editor_menu, menu);
        fillMenuItemIconTintColor(menu.findItem(R.id.fire_brick_menu_item), Color.FIRE_BRICK);
        fillMenuItemIconTintColor(menu.findItem(R.id.orange_red_menu_item), Color.ORANGE_RED);
        fillMenuItemIconTintColor(menu.findItem(R.id.gold_menu_item), Color.GOLD);
        fillMenuItemIconTintColor(menu.findItem(R.id.sea_green_menu_item), Color.SEA_GREEN);
        fillMenuItemIconTintColor(menu.findItem(R.id.slate_blue_menu_item), Color.SLATE_BLUE);
        fillMenuItemIconTintColor(menu.findItem(R.id.steel_blue_menu_item), Color.STEEL_BLUE);
        fillMenuItemIconTintColor(menu.findItem(R.id.cornflower_blue_menu_item), Color.CORNFLOWER_BLUE);
        fillMenuItemIconTintColor(menu.findItem(R.id.medium_aquamarine_menu_item), Color.MEDIUM_AQUAMARINE);
        fillMenuItemIconTintColor(menu.findItem(R.id.dark_magenta_menu_item), Color.DARK_MAGENTA);
        fillMenuItemIconTintColor(menu.findItem(R.id.indian_red_menu_item), Color.INDIAN_RED);
        fillMenuItemIconTintColor(menu.findItem(R.id.dim_gray_menu_item), Color.DIM_GRAY);
        selectColorMenuItem = menu.findItem(R.id.select_color_menu_item);
        fillMenuItemIconTintColor(selectColorMenuItem, note.getColor());
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void fillMenuItemIconTintColor(MenuItem menuItem, Color color) {
        int rgb = getResources().getColor(color.getColorId(), null);
        menuItem.getIcon().setColorFilter(rgb, PorterDuff.Mode.SRC_IN);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.fire_brick_menu_item:
                updateColor(Color.FIRE_BRICK);
                break;
            case R.id.orange_red_menu_item:
                updateColor(Color.ORANGE_RED);
                break;
            case R.id.gold_menu_item:
                updateColor(Color.GOLD);
                break;
            case R.id.sea_green_menu_item:
                updateColor(Color.SEA_GREEN);
                break;
            case R.id.slate_blue_menu_item:
                updateColor(Color.SLATE_BLUE);
                break;
            case R.id.steel_blue_menu_item:
                updateColor(Color.STEEL_BLUE);
                break;
            case R.id.cornflower_blue_menu_item:
                updateColor(Color.CORNFLOWER_BLUE);
                break;
            case R.id.medium_aquamarine_menu_item:
                updateColor(Color.MEDIUM_AQUAMARINE);
                break;
            case R.id.dark_magenta_menu_item:
                updateColor(Color.DARK_MAGENTA);
                break;
            case R.id.indian_red_menu_item:
                updateColor(Color.INDIAN_RED);
                break;
            case R.id.dim_gray_menu_item:
                updateColor(Color.DIM_GRAY);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        super.onPause();
        updateNote();
    }

    private void updateColor(Color color) {
        fillMenuItemIconTintColor(selectColorMenuItem, color);
        note.setColor(color);
        updateNote();
        Toast.makeText(context, R.string.color_changed, Toast.LENGTH_LONG).show();
    }

    private void updateNote() {
        note.setHeader(headerEditText.getText().toString());
        note.setContent(contentEditText.getText().toString());
        repository.updateNote(note);
        controller.onNoteChanged(note);
    }

    public Note getNote() {
        return note;
    }

    public interface Controller {
        void onNoteChanged(Note note);
        boolean inPortraitMode();
    }
}
