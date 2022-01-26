package com.example.gb03_android_on_java_notes.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.gb03_android_on_java_notes.R;

public class YesNoDialog extends DialogFragment implements DialogInterface.OnClickListener {

    private String title = "Title";
    private String message = "Message";
    private OnClickListener onClickYes;
    private OnClickListener onClickNo;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog
                .Builder(getActivity())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.yes, this)
                .setNegativeButton(R.string.no, this)
                .create();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int clickedButton) {
        switch (clickedButton) {
            case Dialog.BUTTON_POSITIVE:
                if (onClickYes != null) onClickYes.onClick();
                break;
            case Dialog.BUTTON_NEGATIVE:
                if (onClickNo != null) onClickNo.onClick();
                break;
        }
    }

    public YesNoDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public YesNoDialog setMessage(String message) {
        this.message = message;
        return this;
    }

    public YesNoDialog setOnClickYes(OnClickListener onClickYes) {
        this.onClickYes = onClickYes;
        return this;
    }

    public YesNoDialog setOnClickNo(OnClickListener onClickNo) {
        this.onClickNo = onClickNo;
        return this;
    }

    public interface OnClickListener {
        void onClick();
    }
}
