package com.rojan.todo.database;

import com.rojan.todo.model.Task;

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

}
