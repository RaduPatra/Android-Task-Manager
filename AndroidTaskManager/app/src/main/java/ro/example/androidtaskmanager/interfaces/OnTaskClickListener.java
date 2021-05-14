package ro.example.androidtaskmanager.interfaces;

import android.view.View;

import ro.example.androidtaskmanager.other.Task;

public interface OnTaskClickListener {
    void onCheckBoxClick(Task task, View view);
}
