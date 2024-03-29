package com.rojan.todo.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProviders;

import com.rojan.todo.SingleTaskFragment;
import com.rojan.todo.model.Task;

import java.util.List;

public class SingleTaskViewPagerAdapter extends FragmentPagerAdapter {

    private List<Task> tasks;

    public void setData(List<Task> tasks){
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    public SingleTaskViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        SingleTaskFragment fragment = (SingleTaskFragment) SingleTaskFragment.getInstance(tasks.get(position).getTaskId(), position);
        System.out.println("get item bata kun patahkao  " + tasks.get(position).getTaskName() + " id chahi " + tasks.get(position).getTaskId());
        System.out.println("yo running cha ki chiana ");
        return fragment;
    }

    @Override
    public int getCount() {
        return tasks == null ? 0 : tasks.size();
    }
}
