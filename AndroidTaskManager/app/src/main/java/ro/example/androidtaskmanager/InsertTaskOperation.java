package ro.example.androidtaskmanager;

import android.os.AsyncTask;

class InsertTaskOperation extends AsyncTask<Task, Void, String> {

    TaskOperations listener;

    InsertTaskOperation(TaskOperations listener){
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Task... tasks) {
        try {
            MyApplication.getAppDatabase().taskDao().insertAll(tasks);
        } catch(Exception e){
            return "error";
        }
        return "success";
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result){
        listener.insertTasks(result);
    }
}
