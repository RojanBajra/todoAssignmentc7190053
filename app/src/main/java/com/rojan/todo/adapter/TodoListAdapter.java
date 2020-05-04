package com.rojan.todo.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.rojan.todo.R;
import com.rojan.todo.model.Task;
import com.rojan.todo.utils.DateFormatUtils;

import java.util.List;

public class TodoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Task> listOfData;
    Context context;
    OnTaskClickListener onTaskClickListener;

    public void setData(List<Task> listOfData) {
        this.listOfData = listOfData;
        notifyDataSetChanged();
    }

    public TodoListAdapter(Context context, OnTaskClickListener onTaskClickListener) {
        this.onTaskClickListener = onTaskClickListener;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return R.layout.stats_for_todo_list;
        } else {
            return R.layout.todo_list;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        View view;
        switch (viewType) {

            case R.layout.stats_for_todo_list:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stats_for_todo_list, parent, false);
                holder = new StatViewHolder(view);
                break;
            case R.layout.todo_list:

            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_list, parent, false);
                holder = new TodoListViewHolder(view, onTaskClickListener);
                break;
        }
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TodoListViewHolder) {
            ((TodoListViewHolder) holder).lblTitle.setText(listOfData.get(position - 1).getTaskName());
            ((TodoListViewHolder) holder).lblDescription.setText(listOfData.get(position - 1).getTaskDescription());
            ((TodoListViewHolder) holder).lblDueDate.setText("Due date: " + DateFormatUtils.getInstance().dateConverter(listOfData.get(position - 1).getTaskDate(), "MM-dd-yyyy"));
            ((TodoListViewHolder) holder).container.setBackground(settingBackground(position - 1));
        } else if (holder instanceof StatViewHolder) {
            ((StatViewHolder) holder).lblToday.setText(generateTotalToday());
            ((StatViewHolder) holder).lblAll.setText(Integer.toString(listOfData.size()));
        }

    }

    @Override
    public int getItemCount() {
        return listOfData == null ? 0 : listOfData.size() + 1;
    }

    private Drawable settingBackground(int position) {
        Drawable drawable = AppCompatResources.getDrawable(context, R.drawable.list_border_background);
        Drawable drawable1 = DrawableCompat.wrap(drawable);
        if (listOfData.get(position).getPriority() == 0) {
            DrawableCompat.setTint(drawable1, ContextCompat.getColor(context, R.color.colorRed));
            return drawable1;
        } else if (listOfData.get(position).getPriority() == 1) {
            DrawableCompat.setTint(drawable1, ContextCompat.getColor(context, R.color.colorOrange));
            return drawable1;
        } else {
            DrawableCompat.setTint(drawable1, ContextCompat.getColor(context, R.color.colorYellowForText));
            return drawable1;
        }
    }

    private String generateTotalToday() {
        int counter = 0;
        for (Task singleTask : listOfData) {
            if (DateFormatUtils.getInstance().generateCurrentDate().equals(DateFormatUtils.getInstance().dateConverter(singleTask.getTaskDate(), "MM-dd-yyyy"))) {
                counter++;
            }
        }
        return "" + counter;
    }

    public class TodoListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView lblTitle;
        private TextView lblDescription;
        private TextView lblDueDate;
        private ConstraintLayout container;
        private OnTaskClickListener onTaskClickListener;

        public TodoListViewHolder(@NonNull View itemView, OnTaskClickListener onTaskClickListener) {
            super(itemView);

            // initializing the variables
            lblTitle = (TextView) itemView.findViewById(R.id.lblTitle);
            lblDescription = (TextView) itemView.findViewById(R.id.lblDescription);
            lblDueDate = (TextView) itemView.findViewById(R.id.lblDueDate);
            container = (ConstraintLayout) itemView.findViewById(R.id.constraintContainer);

            this.onTaskClickListener = onTaskClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onTaskClickListener.onTaskClicked(getAdapterPosition());
        }

    }

    public class StatViewHolder extends RecyclerView.ViewHolder {

        private TextView lblToday, lblAll;

        public StatViewHolder(@NonNull View itemView) {
            super(itemView);

            lblToday = (TextView) itemView.findViewById(R.id.lblToday);
            lblAll = (TextView) itemView.findViewById(R.id.lblAll);
        }

    }

    public interface OnTaskClickListener {
        public void onTaskClicked(int position);
    }
}
