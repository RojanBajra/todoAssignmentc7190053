package com.rojan.todo;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddTaskFragment extends Fragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    Button btnDatePicker, btnTimePicker;
    EditText txtTaskDate, txtTaskTime;
    Activity activity;

    private Context context;
    public AddTaskFragment(Context context, Activity activity) {
        // Required empty public constructor
        this.context = context;
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);
        init(view);
        return view;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(i,i1,i2);
        String dateVal = (DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime()));
        txtTaskDate.setText(dateVal);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        String timeVal = i + ":" + i1;
        txtTaskTime.setText(timeVal);
    }

    private void init(View view){
        // For date picker
        btnDatePicker = (Button) view.findViewById(R.id.btnDatePicker);
        txtTaskDate = (EditText) view.findViewById(R.id.txtTaskDate);

        // For time picker
        btnTimePicker = (Button) view.findViewById(R.id.btnTimePicker);
        txtTaskTime = (EditText) view.findViewById(R.id.txtTaskTime);

        addActionListeners();
    }

    private void addActionListeners(){
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });

        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker();
            }
        });
    }

    private void showDatePicker(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                context,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    private void showTimePicker(){
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                context,
                this,
                Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                Calendar.getInstance().get(Calendar.MINUTE),
                android.text.format.DateFormat.is24HourFormat(getActivity())
        );
        timePickerDialog.show();
    }

}

// paste this in init
//    MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
//        builder.setTitleText("Date picker");
//                builder.setTheme(R.style.MyCalendarTheme);
//final MaterialDatePicker materialDatePicker = builder.build();
//
//
//        btnDatePicker.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View view) {
//        materialDatePicker.show(getFragmentManager(), "Date_Picker");
//        }
//        });