package ro.example.androidtaskmanager.other;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import ro.example.androidtaskmanager.R;
import ro.example.androidtaskmanager.interfaces.OnTaskClickListener;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {


    private final List<Task> taskData;
    //private Context context;
    public static OnTaskClickListener onTaskClickListener;

    public TaskAdapter(List<Task> taskData, OnTaskClickListener listener) {
        this.taskData = taskData;
        onTaskClickListener = listener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_row, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, final int position) {
        holder.bind(taskData.get(position));
    }

    @Override
    public int getItemCount() {
        return taskData.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        // private final TextView desc;
        private final CheckBox checkBox;
        private final TextView reminderText;
        private final ImageButton deleteBtn;
        private final TextView pointsText;
        //private final ConstraintLayout layout;

        public TaskViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.rewardText);
            //desc = view.findViewById(R.id.taskDesc);
            checkBox = view.findViewById(R.id.checkBox);
            reminderText = view.findViewById(R.id.reminderText);
            deleteBtn = view.findViewById(R.id.deleteTaskBtn);
            pointsText = view.findViewById(R.id.priceText);

            //layout = view.findViewById(R.id.taskContainer);
        }

        public void bind(Task item) {
            name.setText(item.getTaskName());
            // desc.setText(item.getTaskDesc());
            checkBox.setChecked(item.isDone);
            Integer i = item.getTaskPoints();
            pointsText.setText(i.toString());



            if (item.dueTime != 0) {
                DateFormat formatter = new SimpleDateFormat("HH:mm:ss", Locale.US);
                formatter.setTimeZone(TimeZone.getTimeZone("GMT+3"));
                String dueTimeText = formatter.format(new Date(item.getDueTime()));
                dueTimeText = "Due time: " + dueTimeText;
                reminderText.setText(dueTimeText);
                reminderText.setVisibility(View.VISIBLE);
            }


            setCheckValue(item);

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                //v = checkbox view ONLY not whole task view!!
                public void onClick(View v) {
                    boolean isChecked = checkBox.isChecked();
                    item.isDone = isChecked;
                    setCheckValue(item);
                    onTaskClickListener.onCheckBoxClick(item, v);
                }
            });

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onTaskClickListener.onDeleteTaskClick(item, v);
                }
            });


        }

        private void setCheckValue(Task item) {
            if (item.isDone)
                name.setPaintFlags(name.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            else
                name.setPaintFlags(name.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }
    }
}

