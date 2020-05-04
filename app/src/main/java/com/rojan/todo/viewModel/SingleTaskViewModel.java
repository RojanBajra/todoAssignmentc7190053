package com.rojan.todo.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.rojan.todo.database.AppDatabase;
import com.rojan.todo.database.Repository;
import com.rojan.todo.model.Task;

import java.util.List;

public class SingleTaskViewModel extends AndroidViewModel {

    int position;
    LiveData<List<Task>> listOfTask;

    public SingleTaskViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(application);
        Repository repository = new Repository(database);
        listOfTask = repository.loadAllTask();
    }

    // getters and setters
    public LiveData<List<Task>> getListOfTask() {
        return listOfTask;
    }

    public void setListOfTask(LiveData<List<Task>> listOfTask) {
        this.listOfTask = listOfTask;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
