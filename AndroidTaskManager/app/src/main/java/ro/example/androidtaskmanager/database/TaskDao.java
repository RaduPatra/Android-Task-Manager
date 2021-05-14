package ro.example.androidtaskmanager.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ro.example.androidtaskmanager.other.Task;

@Dao
public interface TaskDao {

    @Insert
    void insertAll(Task... tasks);//insert(t1,t2,...)

    @Update
    void updateTasks(Task... tasks);//update(t1,t2..)

    @Delete
    void deleteTasks(Task... tasks);


    @Query("SELECT * FROM task")
    List<Task> getAll();


}
