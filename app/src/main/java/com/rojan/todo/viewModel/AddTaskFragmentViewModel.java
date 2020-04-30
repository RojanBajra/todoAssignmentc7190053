package com.rojan.todo.viewModel;

import androidx.lifecycle.ViewModel;

public class AddTaskFragmentViewModel extends ViewModel {

    private String valTitle, valDescription, valDate, valTime, valPriority;

    public AddTaskFragmentViewModel(){
        super();
    }

    public void init(){
        valTitle = "";
        valDescription = "";
        valDate = "";
        valTime = "";
        valPriority = "";
    }

}
