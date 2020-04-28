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
    private Context context;

    public TodoListFragment(Context context) {
        // Required empty public constructor
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_todo_list, container, false);
        listTodo = (RecyclerView) view.findViewById(R.id.listTodo);
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fabAddTask);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAddTask();
            }
        });

        listTodo.setLayoutManager(new LinearLayoutManager(context));
        TodoListAdapter adapter = new TodoListAdapter();
        listTodo.setAdapter(adapter);

        return view;
    }

    public void btnAddTask() {
        startActivity(AddTask.makeIntent(context));
    }
}
