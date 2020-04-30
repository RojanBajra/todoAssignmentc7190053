package com.rojan.todo;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rojan.todo.adapter.TodoListAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class TodoListFragment extends Fragment {

    private RecyclerView listTodo;
    private FloatingActionButton floatingActionButton;

    public TodoListFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_todo_list, container, false);
        init(view);

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
        TodoListAdapter adapter = new TodoListAdapter();
        listTodo.setAdapter(adapter);
    }

    public void btnAddTask() {
        startActivity(AddTask.makeIntent(getActivity()));
    }
}
