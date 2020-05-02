package com.rojan.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.rojan.todo.adapter.SingleTaskViewPagerAdapter;
import com.rojan.todo.database.AppDatabase;
import com.rojan.todo.model.Task;

import java.util.List;

public class SingleTask extends AppCompatActivity {

    private ViewPager viewPagerSingleTask;
    private SingleTaskViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_task);
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
