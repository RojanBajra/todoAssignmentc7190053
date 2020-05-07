package com.rojan.todo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rojan.todo.viewModel.AddCategoryFragmentViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddCategoryFragment extends Fragment {

    private EditText txtCategory;
    private Button btnAddCategory;
    private AddCategoryFragmentViewModel viewModel;
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

        addActionListener();
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
        }else{
            Toast.makeText(getContext(), "Category name cannot be empty", Toast.LENGTH_LONG).show();
        }
    }
}
