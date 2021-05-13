package ro.example.androidtaskmanager;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {


    private List<Task> taskData;
    //private Context context;
    public static OnTaskClickListener onTaskClickListener;

    public TaskAdapter(List<Task> taskData, OnTaskClickListener context) {
        this.taskData = taskData;
        this.onTaskClickListener = context;
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

    /*public void updateItems(List<Task> newList) {
        taskData.clear();
        taskData.addAll(newList);
        this.notifyDataSetChanged();
    }*/

    public static class TaskViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
       // private final TextView desc;
        private final CheckBox checkBox;
        //private final ConstraintLayout layout;

        public TaskViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.taskName);
            //desc = view.findViewById(R.id.taskDesc);
            checkBox = view.findViewById(R.id.checkBox);
            //layout = view.findViewById(R.id.taskContainer);
        }

        public void bind(Task item) {
            name.setText(item.getTaskName());
           // desc.setText(item.getTaskDesc());
            checkBox.setChecked(item.isDone);
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


        }

        private void setCheckValue(Task item) {
            if (item.isDone)
                name.setPaintFlags(name.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            else
                name.setPaintFlags(name.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
        }
    }
}

