package com.rojan.todo.viewModel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.rojan.todo.database.AppDatabase;
import com.rojan.todo.model.Task;

import java.util.List;

public class TodoListFragmentViewModel extends ViewModel {
    private LiveData<List<Task>> listOfTask;

    public LiveData<List<Task>> getListOfTask() {
        return listOfTask;
    }

    public void setListOfTask(LiveData<List<Task>> listOfTask) {
        this.listOfTask = listOfTask;
    }

    public TodoListFragmentViewModel(Application application){
        listOfTask = (AppDatabase.getInstance(application).taskDao().loadAllTheTask());
    }

//    private void retrieveData(){
////        final LiveData<List<Task>> listOfTask = AppDatabase.getInstance(getActivity()).taskDao().loadAllTheTask();
//        listOfTask = (AppDatabase.getInstance(getActivity()).taskDao().loadAllTheTask());
//    }
}
