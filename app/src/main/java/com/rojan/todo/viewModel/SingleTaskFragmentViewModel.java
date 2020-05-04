package com.rojan.todo.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.rojan.todo.model.Task;

import java.util.List;

public class SingleTaskFragmentViewModel extends AndroidViewModel {

    private LiveData<Task> task;
    private int lblPriorityColor;

    public SingleTaskFragmentViewModel(@NonNull Application application, int taskId) {
        super(application);
    }


    public int getLblPriorityColor() {
        return lblPriorityColor;
    }

    public void setLblPriorityColor(int lblPriorityColor) {
        this.lblPriorityColor = lblPriorityColor;
    }

}
