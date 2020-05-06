package com.rojan.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class AddCategory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        if (savedInstanceState == null){
            loadFragment(-1);
        }
    }

    private void loadFragment(int categoryId){
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment addTaskFragment = new AddCategoryFragment(categoryId);
        fragmentManager.beginTransaction().add(R.id.frameContainer, addTaskFragment).commit();
    }
}
