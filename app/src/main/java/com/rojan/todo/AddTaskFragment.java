package com.rojan.todo;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
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
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import com.rojan.todo.database.AppDatabase;
import com.rojan.todo.model.Task;
import com.rojan.todo.utils.DateFormatUtils;
import com.rojan.todo.viewModel.AddTaskFragmentViewModel;
import com.rojan.todo.viewModelFactory.AddTaskFragmentViewModelFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddTaskFragment extends Fragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    public static String EDIT_PAGE_KEY = "editPage";
    public static int DEFAULT_TASK_ID = -1;
    public static final String INSTANCE_TASK_ID = "instanceTaskId";

    private Button btnDatePicker, btnTimePicker, btnAddTask;
    private EditText txtTaskDate, txtTaskTime, txtTitle, txtDescription;
    private TextView lblTaskTitle, lblDescription, lblTaskDate, lblPriority, lblAddTaskTitle;
    private RadioButton radioButtonHigh, radioButtonLow, radioButtonMedium;
    private RadioGroup radioGroup;

    private AddTaskFragmentViewModel viewModel;
    private int taskId;

//    public static Fragment getInstance(int taskId){
//        Bundle args = new Bundle();
//        args.putInt(EDIT_PAGE_KEY, taskId);
//        AddTaskFragment fragment = new AddTaskFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }

    public AddTaskFragment(){

    }

    public AddTaskFragment(int taskId){
        this.taskId = taskId;
    }

//    public AddTaskFragment() {
//        if(taskId == -1){
//            viewModel = ViewModelProviders.of(this).get(AddTaskFragmentViewModel.class);
//        }else {
//
//        }
//        viewModel.setTaskId(taskId);
//    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        System.out.println("while rotating on start " + args);
        if (args != null) {
            taskId = (args.getInt(EDIT_PAGE_KEY));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);
        if (savedInstanceState != null && savedInstanceState.containsKey(INSTANCE_TASK_ID)){
            taskId = savedInstanceState.getInt(INSTANCE_TASK_ID, DEFAULT_TASK_ID);
        }
        init(view);
//        System.out.println("view model ko data " + viewModel.getValTitle());
        setDefaultText();
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(INSTANCE_TASK_ID, taskId);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(i, i1, i2);
        String dateVal = (DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime()));
        txtTaskDate.setText(dateVal);
        viewModel.setValDate((calendar.getTime()));
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        Calendar calendar = Calendar.getInstance();
        System.out.println("is i and i1 printing ? i " + i + " and i1 " + i1);
        calendar.set(i, i1);
        Date c = Calendar.getInstance().getTime();
        calendar.set(c.getYear(), c.getMonth(), c.getDate(), i, i1);
        String timeVal = i + ":" + i1;
        txtTaskTime.setText(timeVal);
        viewModel.setValTime(calendar.getTime());
    }

    private void init(View view) {
        btnAddTask = view.findViewById(R.id.btnAddTask);
        txtTitle = view.findViewById(R.id.txtTitle);
        txtDescription = view.findViewById(R.id.txtDescription);

        lblTaskTitle = view.findViewById(R.id.lblTaskTitle);
        lblDescription = view.findViewById(R.id.lblTaskDesription);
        lblTaskDate = view.findViewById(R.id.lblTaskDate);
        lblPriority = view.findViewById(R.id.lblPriority);
        lblAddTaskTitle = view.findViewById(R.id.lblAddTask);

        radioButtonHigh = view.findViewById(R.id.radioButtonHigh);
        radioButtonLow = view.findViewById(R.id.radioButtonLow);
        radioButtonMedium = view.findViewById(R.id.radioButtonMedium);

        radioGroup = view.findViewById(R.id.radioGroup);

        // For date picker
        btnDatePicker = view.findViewById(R.id.btnDatePicker);
        txtTaskDate = view.findViewById(R.id.txtTaskDate);

        // For time picker
        btnTimePicker = view.findViewById(R.id.btnTimePicker);
        txtTaskTime = view.findViewById(R.id.txtTaskTime);

        // view model
        System.out.println("while rotating " + taskId);
        if (taskId == DEFAULT_TASK_ID){
            System.out.println("while rotating task equal");
            viewModel = ViewModelProviders.of(this).get(AddTaskFragmentViewModel.class);
            taskId = (taskId);
        }else{
            System.out.println("while rotating task not equal");
            AddTaskFragmentViewModelFactory factory = new AddTaskFragmentViewModelFactory(getActivity().getApplication(), taskId);
            viewModel = ViewModelProviders.of(getActivity(), factory).get(AddTaskFragmentViewModel.class);
            viewModel.getTaskToEdit().observe(getActivity(), new Observer<Task>() {
                @Override
                public void onChanged(Task task) {
                    viewModel.getTaskToEdit().removeObserver(this);
                    setEditableData(task);
                }
            });
        }

        //viewModel.init();



        addActionListeners();
    }

    private void setEditableData(Task task){
        txtTitle.setText(task.getTaskName());
        txtDescription.setText(task.getTaskDescription());
        txtTaskDate.setText(DateFormat.getDateInstance(DateFormat.FULL).format(task.getTaskDate()));
        txtTaskTime.setText(task.getTaskTime().getHours() + ":" + task.getTaskTime().getMinutes());
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

    private void btnAddTaskClick() {
        viewModel.setDefaultLabelText();
        setDefaultText();

        viewModel.setDefaultLabelText();
        if (!txtTitle.getText().toString().isEmpty() && !txtDescription.getText().toString().isEmpty() && !txtTaskDate.getText().toString().isEmpty()) {
            viewModel.setValTitle(txtTitle.getText().toString());
            viewModel.setValDescription(txtDescription.getText().toString());
            viewModel.setValPriority(viewModel.retrievePriorityValue(radioGroup));
            if (taskId == DEFAULT_TASK_ID){

                viewModel.saveIntoDatabase(true);

            }else{
                viewModel.saveIntoDatabase(false);
            }
        } else {
            checkEmptyTextField();
        }
        getActivity().finish();

    }

    private void setDefaultText() {
        lblTaskTitle.setText(viewModel.getLblValTitle());
        lblDescription.setText(viewModel.getLblValDescription());
        lblTaskDate.setText(viewModel.getLblValDate());
        lblPriority.setText(viewModel.getLblValPriority());
        btnAddTask.setText(viewModel.getBtnName());
        lblAddTaskTitle.setText(viewModel.getTopLblTitle());

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
        timePickerDialog.updateTime(Calendar.HOUR_OF_DAY, Calendar.MINUTE);
        timePickerDialog.show();
    }

    // do we need to put this in view model?
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

}