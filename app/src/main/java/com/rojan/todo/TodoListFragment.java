package com.rojan.todo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rojan.todo.adapter.TodoListAdapter;
import com.rojan.todo.database.AppDatabase;
import com.rojan.todo.model.Task;
import com.rojan.todo.viewModel.TodoListAdapterViewModel;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TodoListFragment extends Fragment implements TodoListAdapter.OnTaskClickListener {

    private RecyclerView listTodo;
    private FloatingActionButton floatingActionButton;
    private TodoListAdapter adapter;

    public TodoListFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_todo_list, container, false);
        init(view);
        retrieveData();

        return view;
    }

    @Override
    public void onTaskClicked(int position) {
        Intent intent = SingleTask.makeIntent(getActivity(), position);
        startActivity(intent);
    }

    private void init(View view){
        listTodo = (RecyclerView) view.findViewById(R.id.listTodo);
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fabAddTask);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAddTask();
            }
        });

        listTodo.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TodoListAdapter(getActivity(), this);
        listTodo.setAdapter(adapter);

    }

    private void retrieveData(){
        final LiveData<List<Task>> listOfTask = AppDatabase.getInstance(getActivity()).taskDao().loadAllTheTask();
        listOfTask.observe(getActivity(), new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                System.out.println("first ma running");
                adapter.setData(tasks);
            }
        });
    }

    public void btnAddTask() {
        startActivity(AddTask.makeIntent(getActivity()));
    }


}
