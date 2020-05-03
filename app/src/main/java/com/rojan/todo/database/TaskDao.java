package com.rojan.todo.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.rojan.todo.model.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("select * from task order by priority")
    LiveData<List<Task>> loadAllTheTask();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTask(Task task);

    @Update
    void updateTask(Task task);

    @Delete
    void deleteTask(Task task);

    @Query("Update task set isCompleted = :isCompleted where taskId = :taskId")
    void updateIsCompleted(int taskId, boolean isCompleted);

}
