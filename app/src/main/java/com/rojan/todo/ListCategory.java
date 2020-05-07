package com.rojan.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class ListCategory extends AppCompatActivity {

    public static Intent makeIntent(Context context){
        Intent intent = new Intent(context, ListCategory.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_category);
        if (savedInstanceState == null){
            loadFragment(-1);
        }
    }

    private void loadFragment(int taskId){
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment addTaskFragment = new TodoListFragment();
        fragmentManager.beginTransaction().add(R.id.listTodo, addTaskFragment).commit();
    }
}
