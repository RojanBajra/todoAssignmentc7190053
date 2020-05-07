package com.rojan.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.rojan.todo.adapter.TodoListAdapter;
import com.rojan.todo.viewModel.TodoListViewModel;

public class TodoList extends AppCompatActivity {

    private TodoListViewModel viewModel;

    public static Intent makeIntent(Context context){
        return (new Intent(context, TodoList.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);
        viewModel = ViewModelProviders.of(this).get(TodoListViewModel.class);
        if(savedInstanceState == null){
            loadFragment();
        }
    }

    private void loadFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment listTodoFragment = new TodoListFragment();
        fragmentManager.beginTransaction().add(R.id.frameContainer, listTodoFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         switch (item.getItemId()){
             case R.id.menuAddCategroy:
                startActivity(AddCategory.makeIntent(this, -1));
                break;
                
             case R.id.deleteAll:
                 Toast.makeText(this, "All tasks deleted.", Toast.LENGTH_LONG).show();
                 viewModel.deleteAll();
                 break;
                 
             case R.id.deleteCompleted:
                 Toast.makeText(this, "All completed tasks deleted.", Toast.LENGTH_LONG).show();
                 viewModel.deleteCompleted(true);

             default:
                 System.out.println("default");
                 break;
         }
        return super.onOptionsItemSelected(item);
    }
}
