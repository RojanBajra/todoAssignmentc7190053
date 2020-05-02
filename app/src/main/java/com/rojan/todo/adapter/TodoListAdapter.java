package com.rojan.todo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.rojan.todo.R;
import com.rojan.todo.model.Task;
import com.rojan.todo.viewModel.TodoListAdapterViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TodoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

//    List<Task> listOfData;
    TodoListAdapterViewModel viewModel;
    Context context;

    public void setData(List<Task> listOfData){
//        this.listOfData = listOfData;
        viewModel.setListOfData(listOfData);
        notifyDataSetChanged();
    }

    public TodoListAdapter(Context context){
        this.context = context;
        viewModel = ViewModelProviders.of((FragmentActivity) context).get(TodoListAdapterViewModel.class);
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
//        System.out.println("total data is " + listOfData.size());
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

//            ((TodoListViewHolder)holder).lblTitle.setText(listOfData.get(position - 1).getTaskName());
//            ((TodoListViewHolder)holder).lblDescription.setText(listOfData.get(position - 1).getTaskDescription());
//            ((TodoListViewHolder)holder).lblDueDate.setText("Due date: " + dateConverter(listOfData.get(position - 1).getTaskDate(), "MM-dd-yyyy"));

            ((TodoListViewHolder)holder).lblTitle.setText(viewModel.getListOfData().get(position - 1).getTaskName());
            ((TodoListViewHolder)holder).lblDescription.setText(viewModel.getListOfData().get(position - 1).getTaskDescription());
            ((TodoListViewHolder)holder).lblDueDate.setText("Due date: " + dateConverter(viewModel.getListOfData().get(position - 1).getTaskDate(), "MM-dd-yyyy"));
        }else if(holder instanceof StatViewHolder) {
//            ((StatViewHolder)holder).lblToday.setText("1000");
            ((StatViewHolder)holder).lblToday.setText(generateTotalToday());
            ((StatViewHolder)holder).lblAll.setText(Integer.toString(viewModel.getListOfData().size()));
        }

    }

    @Override
    public int getItemCount() {
//        System.out.println("Name is " + listOfData.get(0).getTaskName() + " and id is " + listOfData.get(0).getTaskId());
//        System.out.println("Name is " + listOfData.get(1).getTaskName() + " and id is " + listOfData.get(1).getTaskId());
//        System.out.println("Name is " + listOfData.get(2).getTaskName() + " and id is " + listOfData.get(2).getTaskId());
        return viewModel.getListOfData().size() + 1;
    }

    private String generateTotalToday(){
        int counter = 0;
        for (Task singleTask: viewModel.getListOfData()) {
            System.out.println("\n Checking it:\n name " + singleTask.getTaskName() + " \ncureent date " + generateCurrentDate() + "\n date in task " + dateConverter(singleTask.getTaskDate(), "MM-dd-yyyy") + "\n\n");
            if (generateCurrentDate().equals(dateConverter(singleTask.getTaskDate(), "MM-dd-yyyy"))){
                counter++;
            }
        }
        return "" + counter;
    }

    private String generateCurrentDate(){
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        return df.format(c);
    }

    private String dateConverter(Date date, String datePattern){
//        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
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
