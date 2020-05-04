package com.rojan.todo.viewModel;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.rojan.todo.R;
import com.rojan.todo.adapter.TodoListAdapter;
import com.rojan.todo.model.Task;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TodoListAdapterViewModel extends AndroidViewModel implements Serializable {

    private List<Task> listOfData;
    private Context context;
    private TodoListAdapter.OnTaskClickListener onTaskClickListener;

    public TodoListAdapterViewModel(@NonNull Application application) {
        super(application);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public TodoListAdapter.OnTaskClickListener getOnTaskClickListener() {
        return onTaskClickListener;
    }

    public void setOnTaskClickListener(TodoListAdapter.OnTaskClickListener onTaskClickListener) {
        this.onTaskClickListener = onTaskClickListener;
    }

    public List<Task> getListOfData() {
        return listOfData;
    }

    public void setListOfData(List<Task> listOfData) {
        this.listOfData = listOfData;
    }

    public Drawable settingBackground(int position){
        Drawable drawable = AppCompatResources.getDrawable(getApplication(), R.drawable.list_border_background);
        Drawable drawable1 = DrawableCompat.wrap(drawable);
        if (listOfData.get(position).getPriority() == 0){
            DrawableCompat.setTint(drawable1, ContextCompat.getColor(getApplication(), R.color.colorRed));
            return drawable1;
        }else if (listOfData.get(position).getPriority() == 1){
            DrawableCompat.setTint(drawable1, ContextCompat.getColor(getApplication(), R.color.colorOrange));
            return drawable1;
        }else {
            DrawableCompat.setTint(drawable1, ContextCompat.getColor(getApplication(), R.color.colorYellowForText));
            return drawable1;
        }
    }



    public String generateTotalToday() {
        int counter = 0;
        for (Task singleTask : listOfData) {
            if (generateCurrentDate().equals(dateConverter(singleTask.getTaskDate(), "MM-dd-yyyy"))) {
                counter++;
            }
        }
        return "" + counter;
    }

    public String generateCurrentDate() {
        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        return df.format(c);
    }

    public String dateConverter(Date date, String datePattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        String dateVal = "";
        try {
            dateVal = dateFormat.format(date);
        } catch (Exception e) {

        }
        return dateVal;
    }
}
