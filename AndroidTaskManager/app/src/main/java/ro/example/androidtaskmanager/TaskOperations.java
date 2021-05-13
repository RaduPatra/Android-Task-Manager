package ro.example.androidtaskmanager;

import java.util.List;

public interface TaskOperations {

    void insertTasks(String result);

    void findTask(Task task);

    void getAllTasks(List<Task> tasks);

    void updateTasks(String result);
}
