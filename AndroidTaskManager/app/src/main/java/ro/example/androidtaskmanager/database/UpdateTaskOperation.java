package ro.example.androidtaskmanager.database;

import android.os.AsyncTask;

import ro.example.androidtaskmanager.other.Task;
import ro.example.androidtaskmanager.interfaces.TaskOperations;

public class UpdateTaskOperation extends AsyncTask<Task, Void, String> {

    TaskOperations listener;

    public UpdateTaskOperation(TaskOperations listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Task... tasks) {
        try {
            MyApplication.getAppDatabase().taskDao().updateTasks(tasks);
        } catch (Exception e) {
            return "error";
        }
        return "success";
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        listener.updateTasks(result);
    }
}