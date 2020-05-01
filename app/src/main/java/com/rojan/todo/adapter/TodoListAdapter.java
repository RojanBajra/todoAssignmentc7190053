package com.rojan.todo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rojan.todo.R;
import com.rojan.todo.model.Task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TodoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Task> listOfData;

    public void setData(List<Task> listOfData){
        this.listOfData = listOfData;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return R.layout.stats_for_todo_list;
        }else{
            return R.layout.todo_list;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        View view;
        System.out.println("total data is " + listOfData.size());
        switch (viewType){
            case R.layout.todo_list:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_list, parent, false);
                holder = new TodoListViewHolder(view);
                break;

            case R.layout.stats_for_todo_list:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stats_for_todo_list, parent, false);
                holder = new StatViewHolder(view);
                break;

            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_list, parent, false);
                holder = new TodoListViewHolder(view);
                break;
        }
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TodoListViewHolder){
//            ((TodoListViewHolder)holder).lblTitle.setText("rojan");
//            ((TodoListViewHolder)holder).lblDescription.setText("description of the text will be shown here.");
//            ((TodoListViewHolder)holder).lblDueDate.setText("Due Date is here.");

            ((TodoListViewHolder)holder).lblTitle.setText(listOfData.get(position).getTaskName());
            ((TodoListViewHolder)holder).lblDescription.setText(listOfData.get(position).getTaskDescription());
            ((TodoListViewHolder)holder).lblDueDate.setText("Due date: " + dateConverter(listOfData.get(position).getTaskDate()));
        }else if(holder instanceof StatViewHolder) {
            ((StatViewHolder)holder).lblToday.setText("1000");
            ((StatViewHolder)holder).lblAll.setText(Integer.toString(listOfData.size()));
        }

    }

    @Override
    public int getItemCount() {
        return listOfData.size();
    }

    private String dateConverter(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String dateVal = "";
        try {
            dateVal = dateFormat.format(date);
        }catch (Exception e){

        }
        return dateVal;
    }

    public class TodoListViewHolder extends RecyclerView.ViewHolder{

        TextView lblTitle, lblDescription, lblDueDate;

        public TodoListViewHolder(@NonNull View itemView) {
            super(itemView);

            // initializing the variables
            lblTitle = (TextView) itemView.findViewById(R.id.lblTitle);
            lblDescription = (TextView) itemView.findViewById(R.id.lblDescription);
            lblDueDate = (TextView) itemView.findViewById(R.id.lblDueDate);
        }

    }

    public class StatViewHolder extends RecyclerView.ViewHolder{

        TextView lblToday, lblAll;

        public StatViewHolder(@NonNull View itemView) {
            super(itemView);

            lblToday = (TextView) itemView.findViewById(R.id.lblToday);
            lblAll = (TextView) itemView.findViewById(R.id.lblAll);
        }
    }
}
