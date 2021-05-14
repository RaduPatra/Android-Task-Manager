package ro.example.androidtaskmanager.database;

import android.os.AsyncTask;

import java.util.List;

import ro.example.androidtaskmanager.other.Task;
import ro.example.androidtaskmanager.interfaces.TaskOperations;

public class GetAllTasksOperation extends AsyncTask<Void, Void, List<Task>> {

    TaskOperations listener;

    public GetAllTasksOperation(TaskOperations listener){
        this.listener = listener;
    }

    @Override
    protected List<Task> doInBackground(Void... task) {

        return MyApplication.getAppDatabase().taskDao().getAll();
    }

    @Override
    protected void onPostExecute(List<Task> tasks){
        listener.getAllTasks(tasks);
    }
}