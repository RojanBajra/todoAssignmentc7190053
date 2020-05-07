package com.rojan.todo.database;

import androidx.lifecycle.LiveData;

import com.rojan.todo.model.Category;
import com.rojan.todo.model.Task;

import java.util.List;

public class Repository {
    private TaskDao taskDaoRepository;
    private CategoryDao categoryDaoRepository;

    public Repository(AppDatabase appDatabase) {
        this.taskDaoRepository = appDatabase.taskDao();
        this.categoryDaoRepository = appDatabase.categoryDao();
    }

    // Task Dao queries
    public void insertTask(final Task task){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                taskDaoRepository.insertTask(task);
            }
        });
    }

    public void deleteTheTask(final Task task){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("repository running " + task.getTaskName());
                taskDaoRepository.deleteTask(task);
            }
        });
    }

    public void deleteTheTaskByCategoryId(final int categoryId){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                taskDaoRepository.deleteTaskRelatedToCategory(categoryId);
            }
        });
    }

    public void updateOverallTask(final Task task){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("task name update " + task.getTaskName());
                taskDaoRepository.updateTask(task);
            }
        });
    }

    public LiveData<List<Task>> loadAllTask(){
        return taskDaoRepository.loadAllTheTask();
    }

    public LiveData<Task> loadTaskById(int taskId){
        return taskDaoRepository.loadTaskById(taskId);
    }

    public void deleteAllTask(){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                taskDaoRepository.deleteAllTask();
            }
        });
    }

    public void deleteAllCompletedTask(final boolean value){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                taskDaoRepository.deleteAllCompleted(value);
            }
        });
    }

    public void completeTask(final boolean completedValue, final int taskId){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                taskDaoRepository.completeTask(completedValue, taskId);
            }
        });
    }



    // Category Dao queries
    public void insertCategory(final Category category){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                categoryDaoRepository.insertCategory(category);
            }
        });
    }

    public LiveData<List<Category>> loadAllCategory() {
        return categoryDaoRepository.loadAllTheCAtegory();
    }

    public LiveData<Category> loadEachCategoryById(int categoryId) {
        return categoryDaoRepository.loadEachCategoryById(categoryId);
    }

    public void deleteTheCategory(final Category category){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                categoryDaoRepository.deleteCategory(category);
            }
        });
    }

}
