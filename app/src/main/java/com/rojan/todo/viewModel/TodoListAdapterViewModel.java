package com.rojan.todo.viewModel;

import androidx.lifecycle.ViewModel;

import com.rojan.todo.model.Task;

import java.util.List;

public class TodoListAdapterViewModel extends ViewModel {

    List<Task> listOfData;

    public List<Task> getListOfData() {
        return listOfData;
    }

    public void setListOfData(List<Task> listOfData) {
        this.listOfData = listOfData;
    }
}
