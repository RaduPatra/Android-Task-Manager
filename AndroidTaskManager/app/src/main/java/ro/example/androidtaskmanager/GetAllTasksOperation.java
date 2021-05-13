package ro.example.androidtaskmanager;

import android.os.AsyncTask;

import java.util.List;

public class GetAllTasksOperation extends AsyncTask<Void, Void, List<Task>> {

    TaskOperations listener;

    GetAllTasksOperation(TaskOperations listener){
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