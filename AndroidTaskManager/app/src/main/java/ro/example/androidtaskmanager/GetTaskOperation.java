/*
package ro.example.androidtaskmanager;

import android.os.AsyncTask;

public class GetTaskOperation extends AsyncTask<Integer, Void, Task> {

    TaskOperations listener;

    GetTaskOperation(TaskOperations listener){
        this.listener = listener;
    }

    @Override
    protected Task doInBackground(Integer... ids) {
        int id = ids[0];
        return MyApplication.getAppDatabase().taskDao().getTaskById(id);
    }

    @Override
    protected void onPostExecute(Task user){
        listener.findTask(user);
    }
}
*/
