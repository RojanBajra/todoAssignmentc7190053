package com.rojan.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.rojan.todo.adapter.SingleTaskViewPagerAdapter;
import com.rojan.todo.database.AppDatabase;
import com.rojan.todo.model.Task;
import com.rojan.todo.viewModel.TodoListAdapterViewModel;

import java.util.List;

public class SingleTask extends AppCompatActivity {

    public static String POSITION_CLICKED = "Postion Clicked";
    private ViewPager viewPagerSingleTask;
    private SingleTaskViewPagerAdapter adapter;

    static public Intent makeIntent(Context context, int position){
        Intent intent = new Intent(context, SingleTask.class);
        intent.putExtra(POSITION_CLICKED, position);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_task);

        Intent intent = getIntent();
        System.out.println("position chahi yo aayo " + intent.getIntExtra(POSITION_CLICKED, 10));
        init();
    }

    private void init() {

        viewPagerSingleTask = (ViewPager) findViewById(R.id.viewPagerSingleTask);
        adapter = new SingleTaskViewPagerAdapter(getSupportFragmentManager(), this);
        viewPagerSingleTask.setAdapter(adapter);

        retrieveData();

    }

    private void retrieveData(){

        LiveData<List<Task>> listOfTask = AppDatabase.getInstance(this).taskDao().loadAllTheTask();

        listOfTask.observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                adapter.setData(tasks);
            }
        });
    }
}
