package com.rojan.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.rojan.todo.adapter.TodoListAdapter;

public class TodoList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);
        loadFragment();
    }

    private void loadFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment listTodoFragment = new TodoListFragment(this);
        fragmentManager.beginTransaction().add(R.id.frameContainer, listTodoFragment).commit();
    }

}
