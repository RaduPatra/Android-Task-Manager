package ro.example.androidtaskmanager;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ro.example.androidtaskmanager.database.GetAllTasksOperation;
import ro.example.androidtaskmanager.database.InsertTaskOperation;
import ro.example.androidtaskmanager.database.UpdateTaskOperation;
import ro.example.androidtaskmanager.interfaces.OnTaskClickListener;
import ro.example.androidtaskmanager.interfaces.TaskOperations;
import ro.example.androidtaskmanager.other.Task;
import ro.example.androidtaskmanager.other.TaskAdapter;
import ro.example.androidtaskmanager.utils.Constants;

import static android.content.Context.MODE_PRIVATE;


public class TasksFragment extends Fragment implements OnTaskClickListener, TaskOperations {

    private Button addTaskButton;
    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    public static List<Task> taskList = new ArrayList<>();


    public TasksFragment() {
        super(R.layout.tasks_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeFields(view);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("My tasks");
        SharedPreferences preferences = this.getActivity().getSharedPreferences(Constants.MY_PREFS, MODE_PRIVATE);
        loadFromPrefs(preferences);
        initTasks();

        adapter = new TaskAdapter(taskList, this);
        recyclerView.setAdapter(adapter);

        addTaskButton.setOnClickListener(v -> insertTask());
        setHasOptionsMenu(true);
    }

    private void initializeFields(View view) {
        recyclerView = view.findViewById(R.id.recyclerView1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        addTaskButton = view.findViewById(R.id.addTaskBtn);
    }

    private void loadFromPrefs(SharedPreferences preferences) {
        //loads data from prefs and sets title
        String display = preferences.getString(Constants.SESSION_DATA, "");
        if (display != "") {
            display = display.substring(0, 1).toUpperCase() + display.substring(1);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(display + "'s tasks");
        }
    }

    private void initTasks() {
        taskList.clear();
        new GetAllTasksOperation(this).execute();
        /*for (int i = 0;i<=10;i++){
            Task task = new Task("name" + i, "desc", false);
            movieList.add(task);
        }*/
    }

    public void insertTask() {
        /*Task task1 = new Task(
                "task1",
                "task1desc"
        );*/

        /*
        //String newTask = newTaskText.getText().toString();
        String newTask = "new test";
        Task task = new Task(newTask, "desc", false);
        taskList.add(task);
        adapter.notifyDataSetChanged();
        Task[] taskList = new Task[]{task};
        new InsertTaskOperation(this).execute(taskList);*/

        FragmentTransaction fragment = getFragmentManager().beginTransaction();
        fragment.replace(R.id.flFragment, new AddTaskFragment());
        fragment.addToBackStack(null);
        fragment.commit();

    }

    @Override
    public void insertTasks(String result) {
        /*if (result.equals("success"))
            Toast.makeText(getContext(), "Users inserted in the database sucessfully", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getContext(), "Users inserted in the database failed", Toast.LENGTH_LONG).show();*/
    }


    @Override
    public void findTask(Task task) {

    }

    @Override
    public void getAllTasks(List<Task> tasks) {
        if (tasks.size() != 0) {
            //Toast.makeText(getContext(), tasks.get(0).taskName + " ! ", Toast.LENGTH_LONG).show();
            taskList.clear();
            taskList.addAll(tasks);
            adapter.notifyDataSetChanged();
        } else
            Toast.makeText(getContext(), "fail", Toast.LENGTH_LONG).show();
    }

    @Override
    public void updateTasks(String result) {
        /*if (result.equals("success"))
            Toast.makeText(getContext(), "Users updated in the database sucessfully", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getContext(), "Users updated in the database failed", Toast.LENGTH_LONG).show();*/
    }

    @Override
    public void onCheckBoxClick(Task task, View view) {
        CheckBox clickedCheck = view.findViewById(R.id.checkBox);
        boolean isChecked = clickedCheck.isChecked();
        Task[] taskList = new Task[]{task};
        new UpdateTaskOperation(this).execute(taskList);
        //Log.d(task.toString(), "testupdate");
        //update task with check
        //Toast.makeText(getContext(), task.taskName + " check " + isChecked, Toast.LENGTH_LONG).show();
    }
}
