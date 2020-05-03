package com.rojan.todo.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.rojan.todo.model.Task;

import java.util.List;

public class SingleTaskFragmentViewModel extends ViewModel {

    private Task task;
    private int lblPriorityColor;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public int getLblPriorityColor() {
        return lblPriorityColor;
    }

    public void setLblPriorityColor(int lblPriorityColor) {
        this.lblPriorityColor = lblPriorityColor;
    }

}
