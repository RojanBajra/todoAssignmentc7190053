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

    private SingleTaskViewPagerAdapterViewModel viewModel;
    private Context context;

    public void setData(List<Task> task){
        viewModel.setListOfTask(task);
        notifyDataSetChanged();
    }

    public SingleTaskViewPagerAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        viewModel = ViewModelProviders.of((FragmentActivity) context).get(SingleTaskViewPagerAdapterViewModel.class);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        System.out.println("fragment creation " + position);
        SingleTaskFragment fragment = (SingleTaskFragment) SingleTaskFragment.getInstance(viewModel.getListOfTask().get(position));
        System.out.println("you sakyo hai");
        return fragment;
    }

    @Override
    public int getCount() {
        return viewModel.getListOfTask() == null ? 0 : viewModel.getListOfTask().size();
    }
}
