package com.rojan.todo.viewModel;

import androidx.lifecycle.ViewModel;

import com.rojan.todo.model.Task;

public class SingleTaskFragmentViewModel extends ViewModel {

    private Task task;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
