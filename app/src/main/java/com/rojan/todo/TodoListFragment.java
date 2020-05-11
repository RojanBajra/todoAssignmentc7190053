package com.rojan.todo;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.rojan.todo.adapter.TodoListAdapter;
import com.rojan.todo.model.Task;
import com.rojan.todo.viewModel.TodoListFragmentViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TodoListFragment extends Fragment implements TodoListAdapter.OnTaskClickListener, TodoListAdapter.OnLongPressTaskClickListener {

    private RecyclerView listTodo;
    private FloatingActionButton floatingActionButton;
    private TodoListAdapter adapter;
    private TodoListFragmentViewModel viewModel;
    private CoordinatorLayout coordinatorLayout;

    public TodoListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_todo_list, container, false);
        init(view);
        setupAdapter();
        setupSwipeAction();
        createSnackBar();
        return view;
    }

    @Override
    public void onTaskClicked(int position) {
        startActivity(SingleTask.makeIntent(getActivity(), position - 1));
    }

    @Override
    public void onLongPressed(int taskId, boolean completedValue, String taskName) {
        viewModel.completeTask(!completedValue, taskId);
        String showMsg = (completedValue ? "Incomplete" : "Complete");
        Toast.makeText(getContext(),  "Task " + taskName+ " marked " + showMsg, Toast.LENGTH_LONG).show();
    }

    private void createSnackBar(){
        viewModel.snackBarShow.observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(final Boolean showSnack) {
                if (showSnack) {
                    Snackbar.make(coordinatorLayout, "Task Deleted", Snackbar.LENGTH_LONG)
                            .setAction(
                                    "UNDO",
                                    new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            viewModel.insertRecent();

                                        }
                                    }
                            ).show();
                    viewModel.snackBarShow.setValue(false);
                }
            }
        });
    }

    private void setupSwipeAction() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Task task = ((TodoListAdapter.TodoListViewHolder) viewHolder).getTask();
                Toast.makeText(getContext(), "Task " + task.getTaskName() + " deleted.", Toast.LENGTH_LONG).show();
                viewModel.deleteTask(task);
            }
        }).attachToRecyclerView(listTodo);
    }

    private void init(View view) {
        listTodo = (RecyclerView) view.findViewById(R.id.listTodo);
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fabAddTask);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAddTask();
            }
        });
        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.frameCoordinator);

        viewModel = ViewModelProviders.of(getActivity()).get(TodoListFragmentViewModel.class);
        retrieveTasks();
    }

    private void setupAdapter(){
        listTodo.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TodoListAdapter(getActivity(), this, this);
        listTodo.setAdapter(adapter);
    }

    private void retrieveTasks() {
        viewModel.getListOfTask().observe(getActivity(), new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                adapter.setData(tasks);
            }
        });
    }

    public void btnAddTask() {
        startActivity(AddTask.makeIntent(getActivity()));
    }

}
