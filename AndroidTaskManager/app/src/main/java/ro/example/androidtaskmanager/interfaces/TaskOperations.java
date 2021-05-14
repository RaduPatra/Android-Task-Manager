package ro.example.androidtaskmanager.interfaces;

import java.util.List;

import ro.example.androidtaskmanager.other.Task;

public interface TaskOperations {

    void insertTasks(String result);

    void findTask(Task task);

    void getAllTasks(List<Task> tasks);

    void updateTasks(String result);
}
