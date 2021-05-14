package ro.example.androidtaskmanager.database;

import android.os.AsyncTask;

import ro.example.androidtaskmanager.interfaces.TaskOperations;
import ro.example.androidtaskmanager.other.Task;

public class DeleteTaskOperation extends AsyncTask<Task, Void, String> {

    TaskOperations listener;

    public DeleteTaskOperation(TaskOperations listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Task... tasks) {
        try {
            MyApplication.getAppDatabase().taskDao().deleteTasks(tasks);
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
        listener.deleteTasks(result);
    }
}