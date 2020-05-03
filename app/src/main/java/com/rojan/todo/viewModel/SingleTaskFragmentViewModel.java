package com.rojan.todo.viewModel;

import androidx.lifecycle.ViewModel;

import com.rojan.todo.model.Task;

public class SingleTaskFragmentViewModel extends ViewModel {

    private Task task;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
