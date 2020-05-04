package com.rojan.todo.viewModelFactory;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.rojan.todo.viewModel.SingleTaskFragmentViewModel;

public class SingleTaskFragmentViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private Application application;
    private int taskId;

    public SingleTaskFragmentViewModelFactory(Application application, int taskId) {
        this.application = application;
        this.taskId = taskId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SingleTaskFragmentViewModel(application, taskId);
    }
}
