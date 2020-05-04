package com.rojan.todo.viewModelFactory;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.rojan.todo.viewModel.AddTaskFragmentViewModel;

public class AddTaskFragmentViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private Application application;
    private int taskId;

    public AddTaskFragmentViewModelFactory(Application application, int taskId) {
        this.application = application;
        this.taskId = taskId;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AddTaskFragmentViewModel(application, taskId);
    }
}
