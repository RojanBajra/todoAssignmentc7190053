package com.rojan.todo.database;

import androidx.lifecycle.LiveData;

import com.rojan.todo.model.Task;

import java.util.List;

public class Repository {
    private TaskDao taskDaoRepository;

    public Repository(AppDatabase appDatabase) {
        this.taskDaoRepository = appDatabase.taskDao();
    }

    public TaskDao getTaskDaoRepository() {
        return taskDaoRepository;
    }

    public void setTaskDaoRepository(TaskDao taskDaoRepository) {
        this.taskDaoRepository = taskDaoRepository;
    }

    public void insertTask(final Task task){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                taskDaoRepository.insertTask(task);
            }
        });
    }

    public LiveData<List<Task>> loadAllTask(){
        return taskDaoRepository.loadAllTheTask();
    }

    public LiveData<Task> loadTaskById(int taskId){
        return taskDaoRepository.loadTaskById(taskId);
    }

}
