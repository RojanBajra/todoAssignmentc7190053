package com.rojan.todo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.rojan.todo.model.Category;
import com.rojan.todo.model.Task;
import com.rojan.todo.viewModel.AddTaskFragmentViewModel;
import com.rojan.todo.viewModelFactory.AddTaskFragmentViewModelFactory;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


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
    private Spinner spinner;

    private AddTaskFragmentViewModel viewModel;
    private int taskId;

    public AddTaskFragment(){

    }

    public AddTaskFragment(int taskId){
        this.taskId = taskId;
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
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

        // view model
        if (taskId == DEFAULT_TASK_ID){
            viewModel = ViewModelProviders.of(this).get(AddTaskFragmentViewModel.class);
            taskId = (taskId);
        }else{
            AddTaskFragmentViewModelFactory factory = new AddTaskFragmentViewModelFactory(getActivity().getApplication(), taskId);
            viewModel = ViewModelProviders.of(getActivity(), factory).get(AddTaskFragmentViewModel.class);
            viewModel.getTaskToEdit().observe(getActivity(), new Observer<Task>() {
                @Override
                public void onChanged(final Task task) {
                    viewModel.getTaskToEdit().removeObserver(this);

                    viewModel.getListCategory().observe(getActivity(), new Observer<List<Category>>() {
                        @Override
                        public void onChanged(List<Category> categories) {
                            setEditableData(task);
                            setSpinnerAdapter(categories);
                        }
                    });
                }
            });
        }
        viewModel.getListCategory().observe(getActivity(), new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                setSpinnerAdapter(categories);
            }
        });

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

        spinner = view.findViewById(R.id.listCategory);

        addActionListeners();
    }

    private void setSpinnerAdapter(List<Category> categories){
        List<String> listCategoryName = new ArrayList<>();
        for (Category category: categories) {
            listCategoryName.add(category.getCategoryName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, listCategoryName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setSelection(viewModel.getSpinnerValuePosition());

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                viewModel.setSpinnerValuePosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setEditableData(Task task){
        txtTitle.setText(task.getTaskName());
        txtDescription.setText(task.getTaskDescription());
        txtTaskDate.setText(DateFormat.getDateInstance(DateFormat.FULL).format(task.getTaskDate()));
        txtTaskTime.setText(task.getTaskTime().getHours() + ":" + task.getTaskTime().getMinutes());
        setPriority(task);
        setCategory(task);
    }

    private void setCategory(Task task){
        for (int i = 0; i < viewModel.getListCategory().getValue().size(); i++){
            if(viewModel.getListCategory().getValue().get(i).getCategoryId() == task.getCategoryId()){
                viewModel.setSpinnerValuePosition(i);
                break;
            }
        }
    }

    private void setPriority(Task task){
        switch (task.getPriority()){
            case 0:
                radioButtonHigh.setChecked(true);
                break;

            case 1:
                radioButtonMedium.setChecked(true);
                break;

            default:
                radioButtonLow.setChecked(true);
                break;
        }
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
        if(viewModel.getListCategory().getValue().size() != 0){
            if (!txtTitle.getText().toString().isEmpty() && !txtDescription.getText().toString().isEmpty() && !txtTaskDate.getText().toString().isEmpty()) {
                viewModel.setValTitle(txtTitle.getText().toString());
                viewModel.setValDescription(txtDescription.getText().toString());
                viewModel.setValPriority(viewModel.retrievePriorityValue(radioGroup));
                if (taskId == DEFAULT_TASK_ID){
                    viewModel.saveIntoDatabase(true);
                    getActivity().finish();
                }else{
                    viewModel.saveIntoDatabase(false);
                    getActivity().finish();
                }
            } else {
                Toast.makeText(getContext(), "Fill all required fields.", Toast.LENGTH_LONG).show();
                checkEmptyTextField();
            }
        }else{
            Toast.makeText(getContext(), "Add a category first in Add category page", Toast.LENGTH_LONG).show();
        }

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