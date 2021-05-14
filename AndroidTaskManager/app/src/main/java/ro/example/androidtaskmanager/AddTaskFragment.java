package ro.example.androidtaskmanager;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.Calendar;
import java.util.List;

import ro.example.androidtaskmanager.database.InsertTaskOperation;
import ro.example.androidtaskmanager.interfaces.TaskOperations;
import ro.example.androidtaskmanager.other.Task;

public class AddTaskFragment extends Fragment implements TaskOperations {

    private Button addButton;
    private EditText newTaskText;
    private TimePicker timePicker;
    private CheckBox checkBox;
    private EditText taskPoints;


    public AddTaskFragment() {
        super(R.layout.new_task);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeFields(view);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Add a new task!");
        createNotificationChannel();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTask = newTaskText.getText().toString();
                String pointsText = taskPoints.getText().toString();
                int points = 0;
                if (!"".equals(pointsText)) {
                    points = Integer.parseInt(pointsText);
                }

                long dueTime = 0;
                if (!newTask.equals("")) {
                    //add new task with text


                    if (checkBox.isChecked()) {
                        Intent intent = new Intent(getActivity(), ReminderBroadcast.class);
                        intent.setAction(Long.toString(System.currentTimeMillis()));
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, intent, 0);
                        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);

                        /*long timeAtClick = System.currentTimeMillis();
                        long timeToSend = 5 * 1000;*/

                        //get time on time picker
                        int hour = timePicker.getHour();
                        int minute = timePicker.getMinute();

                        //convert time to milliseconds
                        Calendar startTime = Calendar.getInstance();
                        startTime.set(Calendar.HOUR_OF_DAY, hour);
                        startTime.set(Calendar.MINUTE, minute);
                        startTime.set(Calendar.SECOND, 0);
                        long alarmStartTime = startTime.getTimeInMillis();
                        alarmManager.set(AlarmManager.RTC_WAKEUP, alarmStartTime, pendingIntent);
                        dueTime = alarmStartTime;
                    }

                    addTask(newTask, dueTime, points);

                }
            }

        });


    }

    void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "reminder_channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notify", name, importance);
            NotificationManager notificationManager = requireActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void addTask(String newTask, long dueTime, int points) {
        //newTask = newTaskText.getText().toString();
        Task task = new Task(newTask, "desc", false, dueTime, points);
        Task[] taskList = new Task[]{task};
        new InsertTaskOperation(this).execute(taskList);

        FragmentTransaction fragment = getFragmentManager().beginTransaction();
        fragment.replace(R.id.flFragment, new TasksFragment());
        fragment.addToBackStack(null);
        fragment.commit();
    }

    private void initializeFields(View view) {
        addButton = view.findViewById(R.id.newTaskButton);
        newTaskText = view.findViewById(R.id.newRewardText);
        timePicker = view.findViewById(R.id.timePicker);
        checkBox = view.findViewById(R.id.checkBoxDueTime);
        taskPoints = view.findViewById(R.id.taskPoints);
    }

    @Override
    public void insertTasks(String result) {

    }

    @Override
    public void findTask(Task task) {

    }

    @Override
    public void getAllTasks(List<Task> tasks) {

    }

    @Override
    public void updateTasks(String result) {

    }

    @Override
    public void deleteTasks(String result) {

    }
}
