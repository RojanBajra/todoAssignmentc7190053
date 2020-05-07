package com.rojan.todo.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.rojan.todo.database.AppDatabase;
import com.rojan.todo.database.Repository;

public class TodoListViewModel extends AndroidViewModel {

    private AppDatabase database;
    private Repository repository;

    public TodoListViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(application);
        Repository repository = new Repository(database);
    }

    public void deleteAll(){
    }
}
