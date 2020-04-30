package com.rojan.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class AddTask extends AppCompatActivity {

    public static Intent makeIntent(Context context){
        Intent intent = new Intent(context, AddTask.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        System.out.println("is this calling this again");
//        viewModel = ViewModelProviders.of(this).get(AddTaskViewModel.class);
        if (savedInstanceState == null){
            loadFragment();
        }
    }

    private void loadFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment addTaskFragment = new AddTaskFragment();
        fragmentManager.beginTransaction().add(R.id.frameContainer, addTaskFragment).commit();

//        viewModel.fragment = new AddTaskFragment();
//        fragmentManager.beginTransaction().add(R.id.frameContainer, viewModel.fragment).commit();
    }
}
