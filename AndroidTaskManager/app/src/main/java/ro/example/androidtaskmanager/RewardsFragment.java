package ro.example.androidtaskmanager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ro.example.androidtaskmanager.database.GetAllRewardsOperation;
import ro.example.androidtaskmanager.database.GetAllTasksOperation;
import ro.example.androidtaskmanager.interfaces.OnRewardClickListener;
import ro.example.androidtaskmanager.interfaces.RewardOperations;
import ro.example.androidtaskmanager.other.Reward;
import ro.example.androidtaskmanager.other.RewardAdapter;
import ro.example.androidtaskmanager.other.Task;
import ro.example.androidtaskmanager.other.TaskAdapter;

public class RewardsFragment extends Fragment implements OnRewardClickListener, RewardOperations {


    private Button addTaskButton;
    private RecyclerView recyclerView;
    private RewardAdapter adapter;
    public static List<Reward> rewardList = new ArrayList<>();


    public RewardsFragment() {
        super(R.layout.tasks_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Rewards");
        initializeFields(view);
        initRewards();
        adapter = new RewardAdapter(rewardList, this);
        recyclerView.setAdapter(adapter);
        addTaskButton.setOnClickListener(v -> addReward());
    }

    private void initializeFields(View view) {
        recyclerView = view.findViewById(R.id.recyclerView1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        addTaskButton = view.findViewById(R.id.addTaskBtn);
    }

    public void addReward() {

        //switch to add task fragment
        FragmentTransaction fragment = getFragmentManager().beginTransaction();
        fragment.replace(R.id.flFragment, new AddRewardFragment());
        fragment.addToBackStack(null);
        fragment.commit();

    }

    @Override
    public void onRewardBtnClick(Reward reward, View view) {

    }

    private void initRewards() {
        rewardList.clear();
        new GetAllRewardsOperation(this).execute();
        /*for (int i = 0; i <= 10; i++) {
            Reward reward = new Reward("reward", 10);
            rewardList.add(reward);
        }*/
    }

    @Override
    public void insertRewards(String result) {

    }

    @Override
    public void findReward(Reward reward) {

    }

    @Override
    public void getAllRewards(List<Reward> rewards) {
        if (rewards.size() != 0) {
            //Toast.makeText(getContext(), tasks.get(0).taskName + " ! ", Toast.LENGTH_LONG).show();
            rewardList.clear();
            rewardList.addAll(rewards);
            adapter.notifyDataSetChanged();
        } else {
            //Toast.makeText(getContext(), "fail", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void updateRewards(String result) {

    }

    @Override
    public void deleteRewards(String result) {

    }
}
