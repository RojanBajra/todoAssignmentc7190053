package com.rojan.todo.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.rojan.todo.model.Task;

import java.util.List;

public class SingleTaskViewModel extends ViewModel {
    int position;
    LiveData<List<Task>> listOfTask;

    public LiveData<List<Task>> getListOfTask() {
        return listOfTask;
    }

    public void setListOfTask(LiveData<List<Task>> listOfTask) {
        this.listOfTask = listOfTask;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
