package com.rojan.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.rojan.todo.adapter.TodoListAdapter;

public class TodoList extends AppCompatActivity {

    private RecyclerView listTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);
        init();
    }

    // initializing variables
    private void init(){
        listTodo = (RecyclerView) findViewById(R.id.listTodo);

        listTodo.setLayoutManager(new LinearLayoutManager(this));
        TodoListAdapter adapter = new TodoListAdapter();
        listTodo.setAdapter(adapter);
    }
}
