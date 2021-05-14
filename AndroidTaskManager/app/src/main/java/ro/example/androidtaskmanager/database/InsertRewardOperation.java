package ro.example.androidtaskmanager.database;

import android.os.AsyncTask;

import ro.example.androidtaskmanager.interfaces.RewardOperations;
import ro.example.androidtaskmanager.other.Reward;

public class InsertRewardOperation extends AsyncTask<Reward, Void, String> {

    RewardOperations listener;

    public InsertRewardOperation(RewardOperations listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Reward... rewards) {
        try {
            MyApplication.getAppDatabase().rewardDao().insertAll(rewards);
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
        listener.insertRewards(result);
    }
}