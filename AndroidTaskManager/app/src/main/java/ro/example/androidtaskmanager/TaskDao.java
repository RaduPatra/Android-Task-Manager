package ro.example.androidtaskmanager;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void insertAll(Task... tasks);//insert(t1,t2,...)

    @Update
    void updateTasks(Task... tasks);//update(t1,t2..)

    @Delete
    void delete(Task user);//delete(t1)

    @Query("SELECT * FROM task WHERE task_id = :id")
    Task getTaskById(int id);

    @Query("SELECT * FROM task")
    List<Task> getAll();


}
