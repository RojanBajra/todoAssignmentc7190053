package com.rojan.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

public class SingleTask extends AppCompatActivity {

    ViewPager viewPagerSingleTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_task);
        init();
    }

    private void init() {
        viewPagerSingleTask = (ViewPager) findViewById(R.id.viewPagerSingleTask);

    }
}
