package com.rojan.todo;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.rojan.todo.viewModel.AddTaskFragmentViewModel;

import java.text.DateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddTaskFragment extends Fragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private Button btnDatePicker, btnTimePicker, btnAddTask;
    private EditText txtTaskDate, txtTaskTime, txtTitle, txtDescription;
    private TextView lblTaskTitle, lblDescription, lblTaskDate, lblPriority;

    private AddTaskFragmentViewModel viewModel;

    private Context context;
    private Activity activity;

    public AddTaskFragment(){

    }

//    public AddTaskFragment(Context context, Activity activity) {
//        // Required empty public constructor
//        this.context = context;
//        this.activity = activity;
//    }

    public void setupContext(){
        this.context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);
        init(view);
        System.out.println("is this running oncreate");
        return view;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(i,i1,i2);
        String dateVal = (DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime()));
        txtTaskDate.setText(dateVal);
        viewModel.setValDate(i + "-" + (i1 + 1) + "-" + i2);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        String timeVal = i + ":" + i1;
        txtTaskTime.setText(timeVal);
        viewModel.setValTime(i + ":" + i1);
    }

    private void init(View view){
        System.out.println("is this running init");
        btnAddTask = view.findViewById(R.id.btnAddTask);
        txtTitle = view.findViewById(R.id.txtTitle);
        txtDescription = view.findViewById(R.id.txtDescription);

        lblTaskTitle = view.findViewById(R.id.lblTaskTitle);
        lblDescription = view.findViewById(R.id.lblTaskDesription);
        lblTaskDate = view.findViewById(R.id.lblTaskDate);
        lblPriority = view.findViewById(R.id.lblPriority);

        // For date picker
        btnDatePicker = view.findViewById(R.id.btnDatePicker);
        txtTaskDate = view.findViewById(R.id.txtTaskDate);

        // For time picker
        btnTimePicker = view.findViewById(R.id.btnTimePicker);
        txtTaskTime = view.findViewById(R.id.txtTaskTime);

        // view model
        viewModel = ViewModelProviders.of(this).get(AddTaskFragmentViewModel.class);
        //viewModel.init();

        // setting up context
        setupContext();

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
        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAddTaskClick();
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

    private void btnAddTaskClick(){
        changeTextColorToBlack();
        if(!txtTitle.getText().toString().isEmpty() && !txtDescription.getText().toString().isEmpty() && !txtTaskDate.getText().toString().isEmpty() && !txtTaskTime.getText().toString().isEmpty()){
            viewModel.setValTitle(txtTitle.getText().toString());
            viewModel.setValDescription(txtDescription.getText().toString());
        }else{
            checkEmptyTextField();
        }
    }

    private void checkEmptyTextField(){
        System.out.println("is this running");
        setDefaultText();

        checkingSingleTextField(txtTitle, lblTaskTitle, "Task Title (*required)");
        checkingSingleTextField(txtDescription, lblDescription, "Task Description (*required)");
        checkingSingleTextField(txtTaskDate, lblTaskDate, "Task Date (*required)");
        txtTaskDate.setTextColor(ContextCompat.getColor(context, R.color.colorRed));
        // priority is left to do
    }

    private void checkingSingleTextField(EditText editText, TextView textView, String message){
        if (editText.getText().toString().isEmpty()){
            textView.setText(message);
            textView.setTextColor(ContextCompat.getColor(context, R.color.colorRed));
        }
    }

    private void setDefaultText(){
        lblTaskTitle.setText("Task Title");
        lblDescription.setText("Task Description");
        lblTaskDate.setText("Task Date");
        lblPriority.setText("Priority");
    }

    private void changeTextColorToBlack(){
        lblTaskTitle.setTextColor(ContextCompat.getColor(context, R.color.colorBlack));
        lblDescription.setTextColor(ContextCompat.getColor(context, R.color.colorBlack));
        lblTaskDate.setTextColor(ContextCompat.getColor(context, R.color.colorBlack));
        lblPriority.setTextColor(ContextCompat.getColor(context, R.color.colorBlack));
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