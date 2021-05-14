package ro.example.androidtaskmanager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

import ro.example.androidtaskmanager.database.InsertRewardOperation;
import ro.example.androidtaskmanager.database.InsertTaskOperation;
import ro.example.androidtaskmanager.interfaces.RewardOperations;
import ro.example.androidtaskmanager.other.Reward;
import ro.example.androidtaskmanager.other.Task;

public class AddRewardFragment extends Fragment implements RewardOperations {


    private Button addButton;
    private EditText newRewardText;
    private EditText newPriceText;

    public AddRewardFragment() {
        super(R.layout.new_reward);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Add a new reward!");
        initializeFields(view);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newReward = newRewardText.getText().toString();
                String text = newPriceText.getText().toString();
                int value = 0;

                if (!"".equals(text)) {
                    value = Integer.parseInt(text);
                }
                addReward(newReward, value);
            }
        });
    }

    private void addReward(String newReward, int newPrice) {
        Reward reward = new Reward(newReward, newPrice);
        Reward[] rewards = new Reward[]{reward};
        new InsertRewardOperation(this).execute(rewards);

        FragmentTransaction fragment = getFragmentManager().beginTransaction();
        fragment.replace(R.id.flFragment, new RewardsFragment());
        fragment.addToBackStack(null);
        fragment.commit();
    }


    private void initializeFields(View view) {
        addButton = view.findViewById(R.id.newRewardButton);
        newRewardText = view.findViewById(R.id.newRewardText);
        newPriceText = view.findViewById(R.id.newRewardPrice);
    }

    @Override
    public void insertRewards(String result) {
       /* if(result.equals("success"))
            Toast.makeText(getContext(), "REWARD inserted in the database sucessfully", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getContext(), "REWARD inserted in the database failed", Toast.LENGTH_LONG).show();*/
    }

    @Override
    public void findReward(Reward reward) {

    }

    @Override
    public void getAllRewards(List<Reward> rewards) {

    }

    @Override
    public void updateRewards(String result) {

    }

    @Override
    public void deleteRewards(String result) {

    }
}
