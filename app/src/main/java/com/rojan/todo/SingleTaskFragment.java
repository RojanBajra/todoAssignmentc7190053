package com.rojan.todo;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.rojan.todo.adapter.SingleTaskViewPagerAdapter;
import com.rojan.todo.database.AppDatabase;
import com.rojan.todo.model.Task;
import com.rojan.todo.viewModel.SingleTaskFragmentViewModel;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SingleTaskFragment extends Fragment {

    private static String SERIALIZABLE_VALUE = "getTask";
    private Task taskData;
    private TextView lblTitle, lblDescription, lblDate, lblTime, lblPriority, lblCreatedOn, lblUpdatedOn, lblCompleted;
    private Button btnEdit, btnDelete;

    public static Fragment getInstance(Task task) {
        Bundle args = new Bundle();
        args.putSerializable(SERIALIZABLE_VALUE, task);
        SingleTaskFragment fragment = new SingleTaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public SingleTaskFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        if (args != null) {
            taskData = (Task) args.getSerializable(SERIALIZABLE_VALUE);
        }
        setValues();
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

    private void init(View view) {
        lblTitle = (TextView) view.findViewById(R.id.lblTaskTitle);
        lblDescription = (TextView) view.findViewById(R.id.lblDescription);
        lblDate = (TextView) view.findViewById(R.id.lblTaskDate);
        lblTime = (TextView) view.findViewById(R.id.lblTaskTime);
        lblPriority = (TextView) view.findViewById(R.id.lblPriority);
        lblCreatedOn = (TextView) view.findViewById(R.id.lblCreatedOn);
        lblUpdatedOn = (TextView) view.findViewById(R.id.lblUpdatedOn);
        lblCompleted = (TextView) view.findViewById(R.id.lblCompleted);
        btnEdit = (Button) view.findViewById(R.id.btnEdit);
        btnDelete = (Button) view.findViewById(R.id.btnDelete);
//        checkBoxCompleted.setClickable(false);
        addActionListeners();
    }

    private void setValues(){
        lblTitle.setText(taskData.getTaskName());
        lblDescription.setText(taskData.getTaskDescription());
        lblCreatedOn.setText(dateConverter(taskData.getCreatedOn(), "MM-dd-yyyy"));
        lblUpdatedOn.setText(dateConverter(taskData.getUpdatedOn(), "MM-dd-yyyy"));
        lblDate.setText(DateFormat.getDateInstance(DateFormat.FULL).format(taskData.getTaskDate()));
        lblTime.setText(dateConverter(taskData.getTaskTime(), "HH:mm"));
        lblPriority.setText(getPriorityValue(taskData.getPriority()));
        lblCompleted.setText(taskData.isCompleted() ? "complete" : "incomplete");
        lblCompleted.setTextColor(taskData.isCompleted() ? ContextCompat.getColor(getActivity(), R.color.colorGreen) : ContextCompat.getColor(getActivity(), R.color.colorRed));
    }

    private void addActionListeners(){
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnEditClicked();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnDeleteClicked();
            }
        });
    }

    private void btnEditClicked(){

    }

    private void btnDeleteClicked(){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase.getInstance(getActivity()).taskDao().deleteTask(taskData);
                getActivity().finish();
            }
        });
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

    private String getPriorityValue(int priorityNumber) {
        switch (priorityNumber) {
            case 0:
                lblPriority.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorRed));
                return "HIGH";
            case 1:
                lblPriority.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorOrange));
                return "MEDIUM";
            default:
                lblPriority.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorYellowForText));
                return "LOW";
        }
    }
}
