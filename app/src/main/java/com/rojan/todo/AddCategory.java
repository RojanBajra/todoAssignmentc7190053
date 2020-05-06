package com.rojan.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class AddCategory extends AppCompatActivity {

    private static String CATEGORY_ID_NAME = "categoryIdName";
    private static int DEFAULT_CATEGORY_ID = -1;

    private int categoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        if (savedInstanceState == null && categoryId == DEFAULT_CATEGORY_ID){
            loadFragment(-1);
        }else if (savedInstanceState == null && categoryId != DEFAULT_CATEGORY_ID){
            
        }
    }

    private void loadFragment(int categoryId){
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment addTaskFragment = new AddCategoryFragment(categoryId);
        fragmentManager.beginTransaction().add(R.id.frameContainer, addTaskFragment).commit();
    }
}
