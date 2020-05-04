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

    public static Intent makeIntent(Context context, int id){
        Intent intent = new Intent(context, AddTask.class);
        intent.putExtra(TASK_ID, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        taskId = getIntent().getIntExtra(TASK_ID, DEFAULT_TASK_ID);
        if(taskId != DEFAULT_TASK_ID){
            System.out.println("This is printing " + taskId);
            int taskId = getIntent().getIntExtra(TASK_ID, -1);
            if (taskId != -1 && savedInstanceState == null){
                loadFragment(taskId);
            }
        }else{
            if (savedInstanceState == null){
                loadFragment(-1);
            }
        }

    }

    private void loadFragment(int taskId){
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment addTaskFragment = new AddTaskFragment(taskId);
        fragmentManager.beginTransaction().add(R.id.frameContainer, addTaskFragment).commit();
    }
}
