package ro.example.androidtaskmanager.old;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ro.example.androidtaskmanager.database.GetAllTasksOperation;
import ro.example.androidtaskmanager.database.InsertTaskOperation;
import ro.example.androidtaskmanager.LoginActivity;
import ro.example.androidtaskmanager.interfaces.OnTaskClickListener;
import ro.example.androidtaskmanager.R;
import ro.example.androidtaskmanager.other.Task;
import ro.example.androidtaskmanager.other.TaskAdapter;
import ro.example.androidtaskmanager.interfaces.TaskOperations;
import ro.example.androidtaskmanager.database.UpdateTaskOperation;
import ro.example.androidtaskmanager.utils.Constants;


public class AppMainOld extends AppCompatActivity/* implements TaskOperations, OnTaskClickListener*/ {

    /*private Button logoutBtn;
    private TextView displayInfo;
    private EditText newTaskText;
    private Button addTaskButton;
    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    public static List<Task> movieList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_main_old);

        initializeFields();
        SharedPreferences preferences = getSharedPreferences(Constants.MY_PREFS, MODE_PRIVATE);
        loadFromPrefs(preferences);
        initTasks();

        adapter = new TaskAdapter(movieList, this);
        recyclerView.setAdapter(adapter);




        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(Constants.SESSION_DATA, "");
                editor.apply();
                Intent registerScreen = new Intent(AppMainOld.this, LoginActivity.class);
                startActivity(registerScreen);
            }
        });


        addTaskButton.setOnClickListener(view ->
                insertTasks()
        );

    }

    private void initTasks() {

        movieList.clear();
        new GetAllTasksOperation(this).execute();
        *//*for (int i = 0;i<=10;i++){
            Task task = new Task("name" + i, "desc");
            movieList.add(task);
        }*//*
    }

    private void initializeFields() {
        logoutBtn = findViewById(R.id.logoutButton);
        displayInfo = findViewById(R.id.textViewName);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        newTaskText = findViewById(R.id.newTaskText);
        addTaskButton = findViewById(R.id.addTaskButton);
    }

    private void loadFromPrefs(SharedPreferences preferences) {
        String display = preferences.getString(Constants.SESSION_DATA, "");
        displayInfo.setText(display);
    }


    public void insertTasks() {
        *//*Task task1 = new Task(
                "task1",
                "task1desc"
        );*//*
        String newTask = newTaskText.getText().toString();
        Task task = new Task(newTask, "desc", false);
        movieList.add(task);
        adapter.notifyDataSetChanged();
        Task[] taskList = new Task[]{task};
        new InsertTaskOperation(this).execute(taskList);

    }


    @Override
    public void insertTasks(String result) {
        if(result.equals("success"))
            Toast.makeText(this, "Users inserted in the database sucessfully", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Users inserted in the database failed", Toast.LENGTH_LONG).show();
    }


    @Override
    public void findTask(Task task) {

    }

    @Override
    public void getAllTasks(List<Task> tasks) {
        if(tasks.size() != 0) {
            Toast.makeText(this, tasks.get(0).taskName + " ! ", Toast.LENGTH_LONG).show();
            //adapter.updateItems(tasks);
            movieList.clear();
            movieList.addAll(tasks);
            adapter.notifyDataSetChanged();
        }
        else
            Toast.makeText(this, "fail", Toast.LENGTH_LONG).show();
    }

    @Override
    public void updateTasks(String result) {
        if(result.equals("success"))
            Toast.makeText(this, "Users updated in the database sucessfully", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Users updated in the database failed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCheckBoxClick(Task task, View view) {
        CheckBox clickedCheck = view.findViewById(R.id.checkBox);
        boolean isChecked = clickedCheck.isChecked();
        Task[] taskList = new Task[]{task};
        new UpdateTaskOperation(this).execute(taskList);
        Log.d(task.toString(),"testupdate");
        //update task with check
        Toast.makeText(this, task.taskName + " check " + isChecked , Toast.LENGTH_LONG).show();
    }*/
}
