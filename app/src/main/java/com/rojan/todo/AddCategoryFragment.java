package com.rojan.todo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rojan.todo.adapter.ListCategoryAdapter;
import com.rojan.todo.database.AppDatabase;
import com.rojan.todo.database.Repository;
import com.rojan.todo.model.Category;
import com.rojan.todo.viewModel.AddCategoryFragmentViewModel;
import com.rojan.todo.viewModel.ListCategoryFragmentViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddCategoryFragment extends Fragment {

    private EditText txtCategory;
    private Button btnAddCategory;
    private RecyclerView recyclerView;

    private AddCategoryFragmentViewModel viewModel;
    private ListCategoryAdapter adapter;
    private int categoryId;


    public AddCategoryFragment() {
        // Required empty public constructor
    }

    public AddCategoryFragment(int categoryId){
        this.categoryId = categoryId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        System.out.println("this is calling add category fragment");
        View view = inflater.inflate(R.layout.fragment_add_category, container, false);
        init(view);
        return view;
    }

    private void init(View view){
        viewModel = ViewModelProviders.of(getActivity()).get(AddCategoryFragmentViewModel.class);
        viewModel.setCategoryId(this.categoryId);

        txtCategory = (EditText) view.findViewById(R.id.txtCategory);
        btnAddCategory = (Button) view.findViewById(R.id.btnAddCategory);
        recyclerView = (RecyclerView) view.findViewById(R.id.listCategory);

        addActionListener();
        setupAdapter();
        setupViewModel();
        setupSwipeAction();
    }

    private void setupSwipeAction() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                System.out.println("swipe callign " + viewModel.getListCategory().getValue().get(viewHolder.getAdapterPosition()).getCategoryName());
                AppDatabase database = AppDatabase.getInstance(getActivity());
                Repository repository = new Repository(database);
                repository.deleteTheCategory(viewModel.getListCategory().getValue().get(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recyclerView);

    }

    private void setupViewModel() {
        viewModel = ViewModelProviders.of(getActivity()).get(AddCategoryFragmentViewModel.class);
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

    private void addActionListener(){
        btnAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAddCategory();
            }
        });
    }

    private void btnAddCategory(){
        if (!txtCategory.getText().toString().isEmpty()){
            viewModel.setCategoryName(txtCategory.getText().toString());
            viewModel.saveIntoDatabase();
            txtCategory.setText("");
        }else{
            Toast.makeText(getContext(), "Category name cannot be empty", Toast.LENGTH_LONG).show();
        }
    }
}
