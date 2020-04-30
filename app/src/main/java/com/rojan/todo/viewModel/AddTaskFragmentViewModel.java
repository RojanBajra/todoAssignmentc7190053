package com.rojan.todo.viewModel;

import androidx.lifecycle.ViewModel;

public class AddTaskFragmentViewModel extends ViewModel {

    private String valTitle, valDescription, valDate, valTime, valPriority;

    public AddTaskFragmentViewModel(){
        super();
        valTitle = "";
        valDescription = "";
        valDate = "";
        valTime = "";
        valPriority = "";
    }

    public String getValTitle() {
        return valTitle;
    }

    public void setValTitle(String valTitle) {
        this.valTitle = valTitle;
    }

    public String getValDescription() {
        return valDescription;
    }

    public void setValDescription(String valDescription) {
        this.valDescription = valDescription;
    }

    public String getValDate() {
        return valDate;
    }

    public void setValDate(String valDate) {
        this.valDate = valDate;
    }

    public String getValTime() {
        return valTime;
    }

    public void setValTime(String valTime) {
        this.valTime = valTime;
    }

    public String getValPriority() {
        return valPriority;
    }

    public void setValPriority(String valPriority) {
        this.valPriority = valPriority;
    }
}
