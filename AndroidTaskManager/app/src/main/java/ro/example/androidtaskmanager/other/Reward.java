package ro.example.androidtaskmanager.other;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Reward {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "reward_id")
    public long id;

    public String rewardName;

    public int rewardPrice;


    public Reward(String rewardName, int rewardPrice) {
        this.rewardName = rewardName;
        this.rewardPrice = rewardPrice;
    }

    public long getId() {
        return id;
    }

    public String getRewardName() {
        return rewardName;
    }

    public int getRewardPrice() {
        return rewardPrice;
    }
}
