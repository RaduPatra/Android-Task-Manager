package ro.example.androidtaskmanager.other;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ro.example.androidtaskmanager.R;
import ro.example.androidtaskmanager.interfaces.OnRewardClickListener;

public class RewardAdapter extends RecyclerView.Adapter<RewardAdapter.RewardViewHolder> {


    private final List<Reward> rewardData;
    public static OnRewardClickListener onRewardClickListener;

    public RewardAdapter(List<Reward> rewardData, OnRewardClickListener listener) {
        this.rewardData = rewardData;
        onRewardClickListener = listener;
    }


    @NonNull
    @Override
    public RewardAdapter.RewardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reward_row, parent, false);
        return new RewardAdapter.RewardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RewardAdapter.RewardViewHolder holder, int position) {
        holder.bind(rewardData.get(position));
    }

    @Override
    public int getItemCount() {
        return rewardData.size();
    }

    public static class RewardViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final Button button;
        //private final ConstraintLayout layout;

        public RewardViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.rewardText);
            button = view.findViewById(R.id.rewardBtn);
        }

        public void bind(Reward item) {
            name.setText(item.getRewardName());
            button.setText(String.valueOf(item.getRewardPrice()));

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRewardClickListener.onRewardBtnClick(item, v);
                }
            });


        }

    }
}
