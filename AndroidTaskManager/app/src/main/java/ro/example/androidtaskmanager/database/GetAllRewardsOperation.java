package ro.example.androidtaskmanager.database;

import android.os.AsyncTask;

import java.util.List;

import ro.example.androidtaskmanager.interfaces.RewardOperations;
import ro.example.androidtaskmanager.other.Reward;

public class GetAllRewardsOperation extends AsyncTask<Void, Void, List<Reward>> {

    RewardOperations listener;

    public GetAllRewardsOperation(RewardOperations listener){
        this.listener = listener;
    }

    @Override
    protected List<Reward> doInBackground(Void... reward) {

        return MyApplication.getAppDatabase().rewardDao().getAll();
    }

    @Override
    protected void onPostExecute(List<Reward> rewards){
        listener.getAllRewards(rewards);
    }
}