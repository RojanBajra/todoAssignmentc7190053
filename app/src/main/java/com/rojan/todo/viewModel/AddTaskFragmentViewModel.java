package com.rojan.todo.viewModel;

import android.app.Application;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.rojan.todo.R;
import com.rojan.todo.database.AppDatabase;
import com.rojan.todo.database.Repository;
import com.rojan.todo.model.Task;
import com.rojan.todo.utils.DateFormatUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddTaskFragmentViewModel extends AndroidViewModel {

    private String valTitle, valDescription;
    private Date valDate, valTime;
    private String lblValTitle, lblValDescription, lblValDate, lblValPriority;
    private int lblTitleColor, lblDescriptionColor, lblDateColor, lblPriorityColor;
    private int valPriority;
    private Repository repository;

    public AddTaskFragmentViewModel(@NonNull Application application) {
        super(application);

        AppDatabase database = AppDatabase.getInstance(getApplication());
        repository = new Repository(database);

        valTitle = "";
        valDescription = "";
        valDate = new Date();
        valTime = new Date();
        valPriority = 0;
        setDefaultLabelText();
    }

    public void setDefaultLabelText(){
        lblValTitle = "Task Title";
        lblValDescription = "Task Description";
        lblValDate = "Task Date";
        lblValPriority = "Task Priority";

        lblTitleColor = R.color.colorBlack;
        lblDescriptionColor = R.color.colorBlack;
        lblDateColor = R.color.colorBlack;
        lblPriorityColor = R.color.colorBlack;

    }

    public void saveIntoDatabase(){
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
        Date date = new Date();

        try {
            date = format.parse(DateFormatUtils.getInstance().generateCurrentDate());
        }catch (Exception e){

        }

        final Task task = new Task(
                getValTitle(),
                getValDescription(),
                getValDate(),
                getValTime(),
                false,
                getValPriority(),
                date,
                date
        );


        repository.insertTask(task);

    }

    public int retrievePriorityValue(RadioGroup radioGroup){
        switch (radioGroup.getCheckedRadioButtonId()){
            case R.id.radioButtonHigh:
                return 0;

            case R.id.radioButtonMedium:
                return 1;

            case R.id.radioButtonLow:
            default:
                return 2;
        }
    }

    @NonNull
    @Override
    public String toString() {
        return "Title: " + valTitle + "\nDescription: " + valDescription + "\nDate: " + valDate + "\nTime: " + valTime + "\nPriority" + valPriority;
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

    public Date getValDate() {
        return valDate;
    }

    public void setValDate(Date valDate) {
        this.valDate = valDate;
    }

    public Date getValTime() {
        return valTime;
    }

    public void setValTime(Date valTime) {
        this.valTime = valTime;
    }

    public int getValPriority() {
        return valPriority;
    }

    public void setValPriority(int valPriority) {
        this.valPriority = valPriority;
    }
}
