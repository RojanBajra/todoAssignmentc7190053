package com.rojan.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.rojan.todo.adapter.SingleTaskViewPagerAdapter;
import com.rojan.todo.model.Task;
import com.rojan.todo.viewModel.SingleTaskViewModel;

import java.util.List;

public class SingleTask extends AppCompatActivity {

    public static String POSITION_CLICKED = "Postion Clicked";
    private ViewPager viewPagerSingleTask;
    private SingleTaskViewPagerAdapter adapter;
    private SingleTaskViewModel viewModel;

    static public Intent makeIntent(Context context, int position){
        SingleTaskFragment.currnetPage = -1;
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

    @Override
    protected void onResume() {
        super.onResume();
        viewPagerSingleTask.setCurrentItem(SingleTaskFragment.currnetPage);
        if(SingleTaskFragment.currnetPage != -1){
            viewModel.setPosition(SingleTaskFragment.currnetPage);
        }
    }

    private void init() {
        System.out.println("this is running page");
        viewModel = ViewModelProviders.of(this).get(SingleTaskViewModel.class);
        Intent intent = getIntent();

        viewModel.setPosition(intent.getIntExtra(POSITION_CLICKED, -1));
        viewPagerSingleTask = (ViewPager) findViewById(R.id.viewPagerSingleTask);

        adapter = new SingleTaskViewPagerAdapter(getSupportFragmentManager());
        viewPagerSingleTask.setAdapter(adapter);

        viewModel.getListOfTask().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks){
                adapter.setData(tasks);
                viewPagerSingleTask.setCurrentItem(viewModel.getPosition());
                viewPagerSingleTask.setOffscreenPageLimit(viewModel.getListOfTask().getValue().size());
            }
        });

    }
}
