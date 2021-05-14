package ro.example.androidtaskmanager.other;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity//(tableName = "task_table")
public class Task {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "task_id")
    public long id;

    public String taskName;

    @NonNull
    public String taskDesc;

    public boolean isDone;

    public long dueTime;


    public Task(String taskName, @NonNull String taskDesc, boolean isDone, long dueTime) {
        this.taskName = taskName;
        this.taskDesc = taskDesc;
        this.isDone = isDone;
        this.dueTime = dueTime;
    }

    /*public String getTaskName() {
        return taskName;
    }

    public String getTaskDesc() {
        return taskDesc;
    }*/

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", taskDesc='" + taskDesc + '\'' +
                ", isDone=" + isDone +
                '}';
    }

    public String getTaskName() {
        return taskName;
    }

    @NonNull
    public String getTaskDesc() {
        return taskDesc;
    }

    public long getDueTime() {
        return dueTime;
    }
}
