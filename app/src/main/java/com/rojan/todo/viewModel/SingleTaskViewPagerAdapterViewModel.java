package com.rojan.todo.viewModel;

import androidx.lifecycle.ViewModel;

import com.rojan.todo.model.Task;

import java.util.List;

public class SingleTaskViewPagerAdapterViewModel extends ViewModel {

    private List<Task> listOfTask;

    public List<Task> getListOfTask() {
        return listOfTask;
    }

    public void setListOfTask(List<Task> listOfTask) {
        this.listOfTask = listOfTask;
    }
}
