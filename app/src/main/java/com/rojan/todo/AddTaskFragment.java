package com.rojan.todo;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import com.rojan.todo.picker.DatePickerFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddTaskFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    static final private String DATE_PICKER_TAG = "Date Picker";
    Button btnDatePicker;

    private Context context;
    public AddTaskFragment(Context context) {
        // Required empty public constructor
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);
        init(view);


        return view;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

    }

    private void init(View view){
        btnDatePicker = (Button) view.findViewById(R.id.btnDatePicker);
        addActionListeners();
    }

    private void addActionListeners(){
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment(context);
                datePicker.show(getFragmentManager(), DATE_PICKER_TAG);
            }
        });
    }
}
