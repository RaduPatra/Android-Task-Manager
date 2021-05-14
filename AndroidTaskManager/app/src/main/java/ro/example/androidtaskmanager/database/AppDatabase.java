package ro.example.androidtaskmanager.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import ro.example.androidtaskmanager.other.Reward;
import ro.example.androidtaskmanager.other.Task;

@Database(entities = {Task.class, Reward.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
    public abstract RewardDao rewardDao();
}