package com.rojan.todo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rojan.todo.R;

import java.util.List;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.TodoListViewHolder> {

    List<String> listOfData;

    public void setData(List<String> listOfData){
        this.listOfData = listOfData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TodoListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.todo_list, parent, false);
        return new TodoListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoListViewHolder holder, int position) {
        holder.lblTitle.setText("rojan");
        holder.lblDescription.setText("description of the text will be shown here.");
        holder.lblDueDate.setText("Due Date is here.");
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class TodoListViewHolder extends RecyclerView.ViewHolder{

        TextView lblTitle, lblDescription, lblDueDate;

        public TodoListViewHolder(@NonNull View itemView) {
            super(itemView);
            lblTitle = (TextView) itemView.findViewById(R.id.lblTitle);
            lblDescription = (TextView) itemView.findViewById(R.id.lblDescription);
            lblDueDate = (TextView) itemView.findViewById(R.id.lblDueDate);
        }

    }
}
