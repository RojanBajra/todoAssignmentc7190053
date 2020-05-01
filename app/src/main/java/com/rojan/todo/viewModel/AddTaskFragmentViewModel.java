package com.rojan.todo.viewModel;

import androidx.lifecycle.ViewModel;

import com.rojan.todo.R;

public class AddTaskFragmentViewModel extends ViewModel {

    private String valTitle, valDescription, valDate, valTime, valPriority;
    private String lblValTitle, lblValDescription, lblValDate, lblValPriority;
    private int lblTitleColor, lblDescriptionColor, lblDateColor, lblPriorityColor;

    public AddTaskFragmentViewModel(){
        super();
        valTitle = "";
        valDescription = "";
        valDate = "";
        valTime = "";
        valPriority = "";
        setDefaultLabelText();
    }

    public void setDefaultLabelText(){
        lblValTitle = "Task Title";
        lblValDescription = "Task Description";
        lblValDate = "Task Date (optional)";
        lblValPriority = "Task Priority";

        lblTitleColor = R.color.colorBlack;
        lblDescriptionColor = R.color.colorBlack;
        lblDateColor = R.color.colorBlack;
        lblPriorityColor = R.color.colorBlack;

    }

    public int getLblTitleColor() {
        return lblTitleColor;
    }

    public void setLblTitleColor(int lblTitleColor) {
        this.lblTitleColor = lblTitleColor;
    }

    public int getLblDescriptionColor() {
        return lblDescriptionColor;
    }

    public void setLblDescriptionColor(int lblDescriptionColor) {
        this.lblDescriptionColor = lblDescriptionColor;
    }

    public int getLblDateColor() {
        return lblDateColor;
    }

    public void setLblDateColor(int lblDateColor) {
        this.lblDateColor = lblDateColor;
    }

    public int getLblPriorityColor() {
        return lblPriorityColor;
    }

    public void setLblPriorityColor(int lblPriorityColor) {
        this.lblPriorityColor = lblPriorityColor;
    }

    public String getLblValTitle() {
        return lblValTitle;
    }

    public void setLblValTitle(String lblValTitle) {
        this.lblValTitle = lblValTitle;
    }

    public String getLblValDescription() {
        return lblValDescription;
    }

    public void setLblValDescription(String lblValDescription) {
        this.lblValDescription = lblValDescription;
    }

    public String getLblValDate() {
        return lblValDate;
    }

    public void setLblValDate(String lblValDate) {
        this.lblValDate = lblValDate;
    }

    public String getLblValPriority() {
        return lblValPriority;
    }

    public void setLblValPriority(String lblValPriority) {
        this.lblValPriority = lblValPriority;
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
