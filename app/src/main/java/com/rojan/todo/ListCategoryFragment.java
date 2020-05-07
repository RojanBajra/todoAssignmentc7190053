package com.rojan.todo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rojan.todo.adapter.ListCategoryAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListCategoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private ListCategoryAdapter adapter;

    public ListCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_category, container, false);
        init(view);
        return view;
    }

    private void init(View view){
        recyclerView = view.findViewById(R.id.listCategory);

        setupAdapter();
    }

    private void setupAdapter(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ListCategoryAdapter();
        recyclerView.setAdapter(adapter);
    }
}
