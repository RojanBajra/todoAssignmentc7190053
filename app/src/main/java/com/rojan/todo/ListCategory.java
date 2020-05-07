package com.rojan.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class ListCategory extends AppCompatActivity {

    public static Intent makeIntent(Context context){
        Intent intent = new Intent(context, ListCategory.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_category);
        if (savedInstanceState == null){
            loadFragment();
        }
    }

    private void loadFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment addTaskFragment = new ListCategoryFragment();
        fragmentManager.beginTransaction().add(R.id.frameContainer, addTaskFragment).commit();
    }
}
