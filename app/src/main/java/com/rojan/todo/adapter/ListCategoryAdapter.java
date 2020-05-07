package com.rojan.todo.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rojan.todo.model.Category;

import java.util.List;

public class ListCategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Category> categories;

    public void setData(List<Category> categories){
        this.categories = categories;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
