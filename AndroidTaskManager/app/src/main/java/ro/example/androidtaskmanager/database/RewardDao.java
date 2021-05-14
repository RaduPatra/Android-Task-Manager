package ro.example.androidtaskmanager.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ro.example.androidtaskmanager.other.Reward;

@Dao
public interface RewardDao {

    @Insert
    void insertAll(Reward... rewards);//insert(t1,t2,...)

    @Update
    void updateRewards(Reward... rewards);//update(t1,t2..)

    @Delete
    void deleteRewards(Reward... rewards);


    @Query("SELECT * FROM reward")
    List<Reward> getAll();


}
