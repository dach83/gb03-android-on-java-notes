package com.example.gb03_android_on_java_notes.ui.list;

import static com.example.gb03_android_on_java_notes.utils.NoteUtils.colorCircleDrawable;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gb03_android_on_java_notes.App;
import com.example.gb03_android_on_java_notes.R;
import com.example.gb03_android_on_java_notes.domain.Note;
import com.example.gb03_android_on_java_notes.domain.NoteRepository;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;

public class ListFragment extends Fragment implements NoteViewHolder.Callbacks {

    private final Map<Integer, Integer> notePositionInRecycler = new HashMap<>();
    private NoteRepository repository;
    private NoteAdapter adapter;
    private Context context;
    private Controller controller;
    private RecyclerView recyclerView;

    public static ListFragment getInstance() {
        return new ListFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        if (context instanceof Controller) {
            controller = (Controller) context;
        } else {
            throw new IllegalStateException("Activity must implement ListFragment.Controller");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        repository = App.get(context).getNoteRepository();
        initView(view);
    }

    private void initView(View view) {
        initRecycler(view);
        view.findViewById(R.id.add_note_button).setOnClickListener(this::onClickAddNoteButton);
    }

    private void initRecycler(View view) {
        recyclerView = view.findViewById(R.id.note_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new NoteAdapter(repository.getNotes(), this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.list_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_menu_item:
                showEditorForNewNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBindItem(Note note, int position) {
        notePositionInRecycler.put(note.getId(), position);
    }

    @Override
    public void onClickItem(Note note) {
        controller.onNoteSelected(note);
    }

    @Override
    public boolean onLongClickItem(Note note, View item) {
        //showDeleteNoteDialog(note);
        showPopupNoteMenu(note, item);
        return true;
    }

    private void showPopupNoteMenu(Note note, View item) {
        PopupMenu popupMenu = new PopupMenu(context, item);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.setOnMenuItemClickListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.change_menu_item:
                    controller.onNoteSelected(note);
                    return true;
                case R.id.delete_menu_item:
                    showDeleteNoteDialog(note);
                    return true;
                default:
                    return false;
            }
        });
        popupMenu.show();
    }

    private void showDeleteNoteDialog(Note note) {
        new AlertDialog.Builder(context)
                .setTitle(note.getHeader())
                .setMessage(R.string.ask_delete)
                .setIcon(colorCircleDrawable(context, note.getColor()))
                .setPositiveButton(R.string.yes, (dialog, which) -> {
                    if (repository.removeNote(note.getId())) {
                        int position = positionOf(note);
                        adapter.notifyItemRemoved(position);
                        adapter.notifyItemRangeChanged(position, adapter.getItemCount() - position);
                        Toast.makeText(context, R.string.note_deleted, Toast.LENGTH_SHORT).show();
                        controller.onNoteDeleted(note);
                    }
                })
                .setNegativeButton(R.string.no, null)
                .show();
    }


    private void onClickAddNoteButton(View view) {
        showEditorForNewNote();
    }

    private void showEditorForNewNote() {
        Note note = repository.createNote();
        controller.onNoteSelected(note);

        Snackbar.make(recyclerView, R.string.note_created, BaseTransientBottomBar.LENGTH_LONG)
                .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
                .setBackgroundTint(context.getColor(R.color.peach))
                .show();
    }

    private int positionOf(Note note) {
        Integer position = notePositionInRecycler.get(note.getId());
        return (position != null) ? position : adapter.getItemCount();
    }

    public void notifyNoteChanged(Note note) {
        int position = positionOf(note);
        adapter.notifyItemChanged(position);
    }

    public interface Controller {
        void onNoteSelected(Note note);

        void onNoteDeleted(Note note);
    }

}
