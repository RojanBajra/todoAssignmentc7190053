package com.rojan.todo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rojan.todo.R;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list, parent, false);
        RecyclerView.ViewHolder holder = new CategoryView(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((CategoryView) holder).lblCateogoryName.setText(categories.get(position).getCategoryName());
    }

    @Override
    public int getItemCount() {
        return categories == null ? 0 : categories.size();
    }

    public class CategoryView extends RecyclerView.ViewHolder {

        private TextView lblCateogoryName;

        public CategoryView(@NonNull View itemView) {
            super(itemView);

            lblCateogoryName = (TextView) itemView.findViewById(R.id.lblCategoryName);
        }

    }
}
