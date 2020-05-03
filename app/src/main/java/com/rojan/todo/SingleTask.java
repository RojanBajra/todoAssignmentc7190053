package com.rojan.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.rojan.todo.adapter.SingleTaskViewPagerAdapter;
import com.rojan.todo.database.AppDatabase;
import com.rojan.todo.model.Task;
import com.rojan.todo.viewModel.SingleTaskViewModel;
import com.rojan.todo.viewModel.TodoListAdapterViewModel;

import java.util.List;

public class SingleTask extends AppCompatActivity {

    public static String POSITION_CLICKED = "Postion Clicked";
    private ViewPager viewPagerSingleTask;
    private SingleTaskViewPagerAdapter adapter;
    private SingleTaskViewModel viewModel;

    static public Intent makeIntent(Context context, int position){
        Intent intent = new Intent(context, SingleTask.class);
        intent.putExtra(POSITION_CLICKED, position);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_task);
        init();
    }

    private void init() {
        viewModel = ViewModelProviders.of(this).get(SingleTaskViewModel.class);
        Intent intent = getIntent();
        viewModel.setPosition(intent.getIntExtra(POSITION_CLICKED, 10));

        viewPagerSingleTask = (ViewPager) findViewById(R.id.viewPagerSingleTask);
        adapter = new SingleTaskViewPagerAdapter(getSupportFragmentManager(), this);
        viewPagerSingleTask.setAdapter(adapter);
        viewPagerSingleTask.setCurrentItem(3, false);
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
