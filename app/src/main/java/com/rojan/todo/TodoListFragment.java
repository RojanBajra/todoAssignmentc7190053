package com.rojan.todo;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rojan.todo.adapter.TodoListAdapter;
import com.rojan.todo.database.AppDatabase;
import com.rojan.todo.model.Task;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TodoListFragment extends Fragment {

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
        adapter = new TodoListAdapter(getActivity());
        listTodo.setAdapter(adapter);
    }

    private void retrieveData(){
        LiveData<List<Task>> listOfTask = AppDatabase.getInstance(getActivity()).taskDao().loadAllTheTask();
        listOfTask.observe(getActivity(), new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                adapter.setData(tasks);
            }
        });
    }

    private void retrieveTotalTask(){
        LiveData<Integer> totalTask = AppDatabase.getInstance(getActivity()).taskDao().loadTotalTask();
        totalTask.observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

            }
        });
    }

    public void btnAddTask() {
        startActivity(AddTask.makeIntent(getActivity()));
    }
}
