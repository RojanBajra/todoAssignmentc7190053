package com.rojan.todo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rojan.todo.model.Task;
import com.rojan.todo.viewModel.SingleTaskFragmentViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class SingleTaskFragment extends Fragment {

    private SingleTaskFragmentViewModel viewModel;

    public SingleTaskFragment() {
        // Required empty public constructor
    }

    public SingleTaskFragment(Task task){
        this.viewModel.setTask(task);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_single_task, container, false);
    }
}
