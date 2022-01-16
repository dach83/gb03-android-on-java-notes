package com.example.gb03_android_on_java_notes.ui.editor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.graphics.drawable.DrawableWrapper;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.gb03_android_on_java_notes.App;
import com.example.gb03_android_on_java_notes.R;
import com.example.gb03_android_on_java_notes.domain.Color;
import com.example.gb03_android_on_java_notes.domain.Note;
import com.example.gb03_android_on_java_notes.domain.NoteRepository;
import com.example.gb03_android_on_java_notes.utils.NoteUtils;
import com.google.android.material.textfield.TextInputEditText;

public class EditorActivity extends AppCompatActivity {

    public static final String NOTE_ID_EXTRA_KEY = "note_id_extra_key";

    private Note note;
    private NoteRepository repository;

    private MenuItem selectColorMenuItem;
    private TextInputEditText headerEditText;
    private TextInputEditText contentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        repository = App.get(this).getNoteRepository();

        Intent intent = getIntent();
        setResult(RESULT_OK, intent); // возвращаю тот же интент, в нем лежат все необходимые данные для обновления списка
        if (intent != null && intent.hasExtra(NOTE_ID_EXTRA_KEY)) {
            int noteId = intent.getIntExtra(NOTE_ID_EXTRA_KEY, -1);
            note = repository.findNote(noteId);
        }

        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.editor_menu, menu);
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
        return super.onCreateOptionsMenu(menu);
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

    private void fillMenuItemIconTintColor(MenuItem menuItem, Color color) {
        int rgb = getResources().getColor(color.getColorId(), null);
        menuItem.getIcon().setColorFilter(rgb, PorterDuff.Mode.SRC_IN);
    }

    @Override
    protected void onPause() {
        super.onPause();
        updateNote();
    }

    private void initView() {
        TextView dateTextView = findViewById(R.id.date_text_view);
        headerEditText = findViewById(R.id.header_edit_text);
        contentEditText = findViewById(R.id.content_edit_text);

        if (note != null) {
            dateTextView.setText(NoteUtils.dateToString(note.getDate()));
            headerEditText.setText(note.getHeader());
            contentEditText.setText(note.getContent());
        }
    }

    private void updateColor(Color color) {
        fillMenuItemIconTintColor(selectColorMenuItem, color);
        note.setColor(color);
    }

    private void updateNote() {
        note.setHeader(headerEditText.getText().toString());
        note.setContent(contentEditText.getText().toString());
        repository.updateNote(note);
    }
}