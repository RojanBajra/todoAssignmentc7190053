package com.rojan.todo.viewModel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.rojan.todo.AddTask;
import com.rojan.todo.database.AppDatabase;
import com.rojan.todo.model.Task;

import java.util.List;

public class TodoListFragmentViewModel extends AndroidViewModel {
    private LiveData<List<Task>> listOfTask;

    public TodoListFragmentViewModel(@NonNull Application application) {
        super(application);
        listOfTask = (AppDatabase.getInstance(getApplication()).taskDao().loadAllTheTask());
    }

    public LiveData<List<Task>> getListOfTask() {
        return listOfTask;
    }

    public void setListOfTask(LiveData<List<Task>> listOfTask) {
        this.listOfTask = listOfTask;
    }

}
