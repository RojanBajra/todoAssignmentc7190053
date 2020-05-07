package com.rojan.todo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rojan.todo.adapter.ListCategoryAdapter;
import com.rojan.todo.model.Category;
import com.rojan.todo.viewModel.ListCategoryFragmentViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListCategoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private ListCategoryAdapter adapter;
    private ListCategoryFragmentViewModel viewModel;

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
        recyclerView = (RecyclerView) view.findViewById(R.id.listCategory);

        setupAdapter();
        setupViewModel();
    }

    private void setupViewModel(){
        viewModel = ViewModelProviders.of(this).get(ListCategoryFragmentViewModel.class);
        viewModel.getListCategory().observe(getActivity(), new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                adapter.setData(categories);
            }
        });
    }

    private void setupAdapter(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ListCategoryAdapter();
        recyclerView.setAdapter(adapter);
    }
}
