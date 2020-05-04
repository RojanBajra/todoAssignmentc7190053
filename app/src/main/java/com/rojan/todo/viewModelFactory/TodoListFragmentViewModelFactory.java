package com.rojan.todo.viewModelFactory;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.rojan.todo.viewModel.TodoListFragmentViewModel;

public class TodoListFragmentViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private Application application;

    public TodoListFragmentViewModelFactory(Application application) {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new TodoListFragmentViewModel(application);
    }
}
