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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.rojan.todo.database.AppDatabase;
import com.rojan.todo.model.Task;
import com.rojan.todo.viewModel.AddTaskFragmentViewModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddTaskFragment extends Fragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private Button btnDatePicker, btnTimePicker, btnAddTask;
    private EditText txtTaskDate, txtTaskTime, txtTitle, txtDescription;
    private TextView lblTaskTitle, lblDescription, lblTaskDate, lblPriority;
    private RadioButton radioButtonHigh, radioButtonLow, radioButtonMedium;

    private AddTaskFragmentViewModel viewModel;

    public AddTaskFragment() {

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
        calendar.set(i, i1, i2);
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

    private void init(View view) {
        btnAddTask = view.findViewById(R.id.btnAddTask);
        txtTitle = view.findViewById(R.id.txtTitle);
        txtDescription = view.findViewById(R.id.txtDescription);

        lblTaskTitle = view.findViewById(R.id.lblTaskTitle);
        lblDescription = view.findViewById(R.id.lblTaskDesription);
        lblTaskDate = view.findViewById(R.id.lblTaskDate);
        lblPriority = view.findViewById(R.id.lblPriority);

        radioButtonHigh = view.findViewById(R.id.radioButtonHigh);
        radioButtonLow = view.findViewById(R.id.radioButtonLow);
        radioButtonMedium = view.findViewById(R.id.radioButtonMedium);

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

    private void addActionListeners() {
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

    private void setupUI() {
        lblTaskTitle.setText(viewModel.getLblValTitle());
        lblDescription.setText(viewModel.getLblValDescription());
        lblTaskDate.setText(viewModel.getLblValDate());
        lblPriority.setText(viewModel.getLblValPriority());
        setDefaultTextColor();
    }

    private void setDefaultTextColor(){
        lblTaskTitle.setTextColor(ContextCompat.getColor(getActivity(), viewModel.getLblTitleColor()));
        lblDescription.setTextColor(ContextCompat.getColor(getActivity(), viewModel.getLblDescriptionColor()));
        lblTaskDate.setTextColor(ContextCompat.getColor(getActivity(), viewModel.getLblDateColor()));
        lblPriority.setTextColor(ContextCompat.getColor(getActivity(), viewModel.getLblPriorityColor()));
    }

    private void showDatePicker() {
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

    private void showTimePicker() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                getActivity(),
                this,
                Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                Calendar.getInstance().get(Calendar.MINUTE),
                android.text.format.DateFormat.is24HourFormat(getActivity())
        );
        timePickerDialog.show();
    }

    private void btnAddTaskClick() {
        viewModel.setDefaultLabelText();
        setDefaultText();

        viewModel.setDefaultLabelText();
        if (!txtTitle.getText().toString().isEmpty() && !txtDescription.getText().toString().isEmpty() && !txtTaskDate.getText().toString().isEmpty()) {
            viewModel.setValTitle(txtTitle.getText().toString());
            viewModel.setValDescription(txtDescription.getText().toString());
            retrievePriorityValue();
            System.out.println("\n\n\n\n this is the value displayed \n\n\n");
            System.out.println(viewModel.toString());
            saveIntoDatabase();
        } else {
            checkEmptyTextField();
        }
    }

    private void saveIntoDatabase(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");

        Date date = new Date();
        Date taskDate = new Date();
        Date taskTime = new Date();

        try {
            date = format.parse(generateCurrentDate());
            taskDate = format.parse(viewModel.getValDate());
            taskTime = format.parse(viewModel.getValTime());
        }catch (Exception e){

        }

        final Task task = new Task(
                viewModel.getValTitle().toString(),
                viewModel.getValDescription(),
                taskDate,
                taskTime,
                false,
                viewModel.getValPriority(),
                date,
                date
        );

        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase.getInstance(getActivity()).taskDao().insertTask(task);
                System.out.println("THis shows that the db has been added");
            }
        });

        getActivity().finish();
    }

    private String generateCurrentDate(){
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        return df.format(c);
    }

    private void checkEmptyTextField() {

        if (txtTitle.getText().toString().isEmpty()) {
            viewModel.setLblValTitle("Task Title (*required)");
            viewModel.setLblTitleColor(R.color.colorRed);
            lblTaskTitle.setText(viewModel.getLblValTitle());
            lblTaskTitle.setTextColor(ContextCompat.getColor(getActivity(), viewModel.getLblTitleColor()));
        }

        if (txtDescription.getText().toString().isEmpty()) {
            viewModel.setLblValDescription("Task Description (*required)");
            viewModel.setLblDescriptionColor(R.color.colorRed);
            lblDescription.setText(viewModel.getLblValDescription());
            lblDescription.setTextColor(ContextCompat.getColor(getActivity(), viewModel.getLblDescriptionColor()));
        }

        if (txtTaskDate.getText().toString().isEmpty()) {
            viewModel.setLblValDate("Task Date (*required)");
            viewModel.setLblDateColor(R.color.colorRed);
            lblTaskDate.setText(viewModel.getLblValDate());
            lblTaskDate.setTextColor(ContextCompat.getColor(getActivity(), viewModel.getLblDateColor()));
        }

    }

    private void setDefaultText() {
        lblTaskTitle.setText(viewModel.getLblValTitle());
        lblDescription.setText(viewModel.getLblValDescription());
        lblTaskDate.setText(viewModel.getLblValDate());
        lblPriority.setText(viewModel.getLblValPriority());
        setDefaultTextColor();
    }

    private void retrievePriorityValue(){
        if (radioButtonHigh.isChecked()){
            viewModel.setValPriority(0);
        }else if (radioButtonMedium.isChecked()){
            viewModel.setValPriority(1);
        }else if (radioButtonLow.isChecked()){
            viewModel.setValPriority(2);
        }
    }

}