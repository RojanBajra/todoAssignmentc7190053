package com.rojan.todo.viewModel;

import android.app.Application;
import android.widget.Toast;

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
        repository = new Repository(database);
    }

    public void deleteAll(){
        repository.deleteAllTask();
        Toast.makeText(getApplication(), "All tasks deleted.", Toast.LENGTH_LONG).show();
    }

    public void deleteCompleted(boolean value){
        repository.deleteAllCompletedTask(value);
        Toast.makeText(getApplication(), "All completed tasks deleted.", Toast.LENGTH_LONG).show();
    }
}
