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

    public AddTaskFragment(){

    }

//    public AddTaskFragment(Context context, Activity activity) {
//        // Required empty public constructor
//        this.context = context;
//        this.activity = activity;
//    }

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
        lblTaskTitle.setText("Task Title");
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

        setupUI();

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

    private void setupUI(){
        lblTaskTitle.setText(viewModel.getLblValTitle());
        lblDescription.setText(viewModel.getLblValDescription());
        lblTaskDate.setText(viewModel.getLblValDate());
        lblPriority.setText(viewModel.getLblValPriority());

        lblTaskTitle.setTextColor(ContextCompat.getColor(getActivity(), viewModel.getLblTitleColor()));
        lblDescription.setTextColor(ContextCompat.getColor(getActivity(), viewModel.getLblDescriptionColor()));
        lblTaskDate.setTextColor(ContextCompat.getColor(getActivity(), viewModel.getLblDateColor()));
        lblPriority.setTextColor(ContextCompat.getColor(getActivity(), viewModel.getLblPriorityColor()));

    }

    private void showDatePicker(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getActivity(),
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
                getActivity(),
                this,
                Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                Calendar.getInstance().get(Calendar.MINUTE),
                android.text.format.DateFormat.is24HourFormat(getActivity())
        );
        timePickerDialog.show();
    }

    private void btnAddTaskClick(){
        viewModel.setDefaultLabelText();
        setDefaultText();

        changeTextColorToBlack();
        viewModel.setDefaultLabelText();
        if(!txtTitle.getText().toString().isEmpty() && !txtDescription.getText().toString().isEmpty() && !txtTaskDate.getText().toString().isEmpty() && !txtTaskTime.getText().toString().isEmpty()){
            viewModel.setValTitle(txtTitle.getText().toString());
            viewModel.setValDescription(txtDescription.getText().toString());
        }else{
            checkEmptyTextField();
        }
    }

    private void checkEmptyTextField(){
        System.out.println("is this running");


        if(txtTitle.getText().toString().isEmpty()){
            viewModel.setLblValTitle("Task Title (*required)");
            viewModel.setLblTitleColor(R.color.colorRed);
            lblTaskTitle.setText(viewModel.getLblValTitle());
            lblTaskTitle.setTextColor(ContextCompat.getColor(getActivity(), viewModel.getLblTitleColor()));
        }

        if(txtDescription.getText().toString().isEmpty()){
            viewModel.setLblValDescription("Task Title (*required)");
            viewModel.setLblDescriptionColor(R.color.colorRed);
            lblDescription.setText(viewModel.getLblValDescription());
            lblDescription.setTextColor(ContextCompat.getColor(getActivity(), viewModel.getLblDescriptionColor()));
        }

        if(txtTaskDate.getText().toString().isEmpty()){
            viewModel.setLblValDate("Task Title (*required)");
            viewModel.setLblDateColor(R.color.colorRed);
            lblTaskDate.setText(viewModel.getLblValDate());
            lblTaskDate.setTextColor(ContextCompat.getColor(getActivity(), viewModel.getLblDateColor()));
        }

//        viewModel.setLblValDescription("Task Description (*required)");
//        viewModel.setLblValDate("Task Date (*required)");
////        viewModel.setLblValPriority("Task Title (*required)");
//
//        checkingSingleTextField(txtTitle, lblTaskTitle, viewModel.getLblValTitle());
//        checkingSingleTextField(txtDescription, lblDescription, viewModel.getLblValDescription());
//        checkingSingleTextField(txtTaskDate, lblTaskDate, viewModel.getLblValDate());
//        txtTaskDate.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorRed));
        // priority is left to do
    }

    private void checkingSingleTextField(EditText editText, TextView textView){
        if (editText.getText().toString().isEmpty()){
            viewModel.setLblValTitle("Task Title (*required)");
            textView.setText("");
            textView.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorRed));
        }
    }

    private void setDefaultText(){
        lblTaskTitle.setText(viewModel.getLblValTitle());
        lblDescription.setText(viewModel.getLblValDescription());
        lblTaskDate.setText(viewModel.getLblValDate());
        lblPriority.setText(viewModel.getLblValPriority());
    }

    private void changeTextColorToBlack(){
        lblTaskTitle.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorBlack));
        lblDescription.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorBlack));
        lblTaskDate.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorBlack));
        lblPriority.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorBlack));
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