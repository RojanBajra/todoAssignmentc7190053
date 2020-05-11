package com.rojan.todo.viewModel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.rojan.todo.AddTask;
import com.rojan.todo.SingleTaskFragment;
import com.rojan.todo.database.AppDatabase;
import com.rojan.todo.database.Repository;
import com.rojan.todo.model.Task;

import java.util.List;

public class TodoListFragmentViewModel extends AndroidViewModel {

    private LiveData<List<Task>> listOfTask;
    private Repository repository;
    public MutableLiveData<Boolean> snackBarShow;
    private Task recentData;

    public TodoListFragmentViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(application);
        repository = new Repository(database);
        listOfTask = (repository.loadAllTask());
        snackBarShow =  new MutableLiveData<>();
    }

    public MutableLiveData<Boolean> getSnackBarShow() {
        return snackBarShow;
    }

    public LiveData<List<Task>> getListOfTask() {
        return listOfTask;
    }

    public void completeTask(boolean completedValue, int taskId){
        repository.completeTask(completedValue, taskId);
    }

    public void deleteTask(Task task){
        recentData = task;
        repository.deleteTheTask(task);
        snackBarShow.setValue(true);
    }

    public void insertRecent(){
        repository.insertTask(recentData);
    }

}
