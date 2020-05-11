package com.rojan.todo.viewModel;

import android.app.Application;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.rojan.todo.R;
import com.rojan.todo.database.AppDatabase;
import com.rojan.todo.database.Repository;
import com.rojan.todo.model.Category;
import com.rojan.todo.model.Task;

import java.util.List;

public class SingleTaskFragmentViewModel extends AndroidViewModel {

    private Repository repository;

    public SingleTaskFragmentViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(application);
        repository = new Repository(database);
    }

    public void deleteTask(Task task){
        repository.deleteTheTask(task);
    }

    public String getPriorityValue(int priorityNumber, TextView lblPriority) {
        switch (priorityNumber) {
            case 0:
                lblPriority.setTextColor(ContextCompat.getColor(getApplication(), R.color.colorRed));
                return "HIGH";
            case 1:
                lblPriority.setTextColor(ContextCompat.getColor(getApplication(), R.color.colorOrange));
                return "MEDIUM";
            default:
                lblPriority.setTextColor(ContextCompat.getColor(getApplication(), R.color.colorYellowForText));
                return "LOW";
        }
    }

    public LiveData<Task>  loadTaskById(int taskId){
        return repository.loadTaskById(taskId);
    }

    public LiveData<Category> loadEachCategoryById(int categoryId){
        return repository.loadEachCategoryById(categoryId);
    }

}
