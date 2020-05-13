package com.rojan.todo;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rojan.todo.model.Category;
import com.rojan.todo.model.Task;
import com.rojan.todo.utils.DateFormatUtils;
import com.rojan.todo.viewModel.SingleTaskFragmentViewModel;

import java.text.DateFormat;


/**
 * A simple {@link Fragment} subclass.
 *
 * not using a view model here as all the pages in the view pager are pointing out to the same view model
 */
public class SingleTaskFragment extends Fragment {

    private static String SERIALIZABLE_VALUE = "getTask";
    private static String TASK_ID = "taskId";
    private static String PAGE_NUMBER = "pageNumber";
    public static int currnetPage = -1;

    private Task taskData;
    private Category listCategoryData;
    private int taskId;
    private int pageNumber;
    private LiveData<Task> taskDataSingle;
    private TextView lblTitle, lblDescription, lblDate, lblTime, lblPriority, lblCreatedOn, lblUpdatedOn, lblCompleted, lblCategory;
    private Button btnEdit, btnDelete;
    private SingleTaskFragmentViewModel viewModel;

    private LiveData<Category> listCategory;

    public static Fragment getInstance(int taskId, int pageNumber) {
        System.out.println("i am running delete get instance");
        Bundle args = new Bundle();
//        args.putSerializable(SERIALIZABLE_VALUE, task);
        args.putInt(TASK_ID, taskId);
        args.putInt(PAGE_NUMBER, pageNumber);
        SingleTaskFragment fragment = new SingleTaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public SingleTaskFragment() {
        // Required empty public constructor
        System.out.println("i am running delete constructor");
        taskData = new Task();
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();

        viewModel = ViewModelProviders.of(getActivity()).get(SingleTaskFragmentViewModel.class);

        if (args != null) {
            taskId = (int) args.getInt(TASK_ID);
            pageNumber = (int) args.getInt(PAGE_NUMBER);

            taskDataSingle = viewModel.loadTaskById(taskId);

            taskDataSingle.observe(getActivity(), new Observer<Task>() {
                @Override
                public void onChanged(Task task) {
                    taskDataSingle.removeObserver(this);
                    taskData = task;
                    listCategory = viewModel.loadEachCategoryById(taskData.getCategoryId());
                    listCategory.observe(getActivity(), new Observer<Category>() {
                        @Override
                        public void onChanged(Category category) {
                            listCategory.removeObserver(this);
                            listCategoryData = category;
                            setValues();
                        }
                    });
                }
            });
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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
        lblCategory = (TextView) view.findViewById(R.id.lblCategory);
        btnEdit = (Button) view.findViewById(R.id.btnEdit);
        btnDelete = (Button) view.findViewById(R.id.btnDelete);

        addActionListeners();
    }

    private void addActionListeners() {
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

    private void setValues() {
        lblTitle.setText(taskData.getTaskName());
        lblDescription.setText(taskData.getTaskDescription());
        lblCreatedOn.setText("Created on: " + DateFormatUtils.getInstance().dateConverter(taskData.getCreatedOn(), "MM-dd-yyyy"));
        lblUpdatedOn.setText("Updated on: " + DateFormatUtils.getInstance().dateConverter(taskData.getUpdatedOn(), "MM-dd-yyyy"));
        lblDate.setText(DateFormat.getDateInstance(DateFormat.FULL).format(taskData.getTaskDate()));
        lblTime.setText(DateFormatUtils.getInstance().dateConverter(taskData.getTaskTime(), "HH:mm"));
        lblPriority.setText(viewModel.getPriorityValue(taskData.getPriority(), lblPriority));
        lblCompleted.setText(taskData.isCompleted() ? "complete" : "incomplete");
        lblCompleted.setTextColor(taskData.isCompleted() ? ContextCompat.getColor(getActivity(), R.color.colorGreen) : ContextCompat.getColor(getActivity(), R.color.colorRed));
        lblCategory.setText("Category: " + listCategoryData.getCategoryName());
//        lblCategory.setText("" + taskData.getCategoryId());
    }

    private void btnEditClicked() {
        currnetPage = this.pageNumber;
        startActivity(AddTask.makeIntent(getActivity(), taskData.getTaskId()));
    }

    private void btnDeleteClicked() {
        viewModel.deleteTask(taskData);
        Toast.makeText(getContext(), "Deleted task.", Toast.LENGTH_LONG).show();
        getActivity().finish();
    }
}
