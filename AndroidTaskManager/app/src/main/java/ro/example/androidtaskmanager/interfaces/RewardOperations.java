package ro.example.androidtaskmanager.interfaces;

import java.util.List;

import ro.example.androidtaskmanager.other.Reward;

public interface RewardOperations {

    void insertRewards(String result);

    void findReward(Reward reward);

    void getAllRewards(List<Reward> rewards);

    void updateRewards(String result);

    void deleteRewards(String result);
}
