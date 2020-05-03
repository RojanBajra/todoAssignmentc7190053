package com.rojan.todo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.rojan.todo.model.Task;
import com.rojan.todo.viewModel.SingleTaskFragmentViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class SingleTaskFragment extends Fragment {

    private static String SERIALIZABLE_VALUE = "getTask";
    private SingleTaskFragmentViewModel viewModel;
    private TextView lblTitle, lblDescription, lblDate, lblTime, lblPriority, lblCreatedOn, lblUpdatedOn;
    private CheckBox checkBoxCompleted;

    public static Fragment getInstance(Task task){
        Bundle args = new Bundle();
        args.putSerializable(SERIALIZABLE_VALUE, task);
        SingleTaskFragment fragment = new SingleTaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public SingleTaskFragment() {
        // Required empty public constructor
    }

    public SingleTaskFragment(int position, Task task){
        viewModel.setPosition(position);
        viewModel.setTask(task);
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("first");
        Bundle args = getArguments();
        if (args != null){
            System.out.println("second");
            viewModel = ViewModelProviders.of(getActivity()).get(SingleTaskFragmentViewModel.class);
            viewModel.setTask((Task) args.getSerializable(SERIALIZABLE_VALUE));
            Task task = (Task) args.getSerializable(SERIALIZABLE_VALUE);
            System.out.println("task  name is " + task.getTaskName());
            setValues();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        System.out.println("third");
        View view = inflater.inflate(R.layout.fragment_single_task, container, false);
        init(view);
        return view;
    }

    private void init(View view){
        lblTitle = (TextView) view.findViewById(R.id.lblTaskTitle);
        lblDescription = (TextView) view.findViewById(R.id.lblDescription);
        lblDate = (TextView) view.findViewById(R.id.lblTaskDate);
        lblTime = (TextView) view.findViewById(R.id.lblTaskTime);
        lblPriority = (TextView) view.findViewById(R.id.lblPriority);
        lblCreatedOn = (TextView) view.findViewById(R.id.lblCreatedOn);
        lblUpdatedOn = (TextView) view.findViewById(R.id.lblUpdatedOn);
        checkBoxCompleted = (CheckBox) view.findViewById(R.id.checkBoxCompleted);
    }

    private void setValues(){
        lblTitle.setText(viewModel.getTask().getTaskName());
        lblDescription.setText(viewModel.getTask().getTaskDescription());
        lblDate.setText(dateConverter(viewModel.getTask().getTaskDate(), "MM-dd-yyyy"));
        lblTime.setText(dateConverter(viewModel.getTask().getTaskTime(), "HH:MM"));
        lblPriority.setText(getPriorityValue(viewModel.getTask().getPriority()));
        lblCreatedOn.setText(dateConverter(viewModel.getTask().getCreatedOn(), "MM-dd-yyyy"));
        lblUpdatedOn.setText(dateConverter(viewModel.getTask().getUpdatedOn(), "MM-dd-yyyy"));
        checkBoxCompleted.setChecked(viewModel.getTask().isCompleted());
    }

    private String dateConverter(Date date, String datePattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        String dateVal = "";
        try {
            dateVal = dateFormat.format(date);
        } catch (Exception e) {

        }
        return dateVal;
    }

    private String getPriorityValue(int priorityNumber){
        switch (priorityNumber){
            case 0:
                return "HIGH";
            case 1:
                return "MEDIUM";
            default:
                return "LOW";
        }
    }
}