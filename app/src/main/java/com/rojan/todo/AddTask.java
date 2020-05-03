package com.rojan.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class AddTask extends AppCompatActivity {

    public static String TASK_ID = "taskId";
    public static int DEFAULT_TASK_ID = -1;
    private int taskId;

    public static Intent makeIntent(Context context){
        Intent intent = new Intent(context, AddTask.class);
        return intent;
    }

    public static Intent makeIntent(Context context, int id, String yoyo){
        Intent intent = new Intent(context, AddTask.class);
        intent.putExtra(TASK_ID, id);
        intent.putExtra("yoyo", yoyo);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        System.out.println("is this calling this again");
//        viewModel = ViewModelProviders.of(this).get(AddTaskViewModel.class);
        taskId = getIntent().getIntExtra(TASK_ID, DEFAULT_TASK_ID);
        if(taskId != DEFAULT_TASK_ID){
            System.out.println("This is printing " + taskId);
            String ff = getIntent().getStringExtra("yoyo");
            System.out.println("this is printing adaing " + ff);
        }
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
