package com.rojan.todo;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.DateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddTaskFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    static final private String DATE_PICKER_TAG = "Date Picker";
    Button btnDatePicker;
    EditText txtTaskDate;

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
        System.out.println("THis is called or not");
        Log.d("Hello", "Yo call vaira cha ki chaina");
        Calendar calendar = Calendar.getInstance();
        calendar.set(i,i1,i2);
        String dateVal = (DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime()));
        System.out.println("date value is " + dateVal);
        txtTaskDate.setText(dateVal);
    }

    private void init(View view){
        btnDatePicker = (Button) view.findViewById(R.id.btnDatePicker);
        txtTaskDate = (EditText) view.findViewById(R.id.txtTaskDate);


        addActionListeners();
    }

    private void addActionListeners(){
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                DialogFragment datePicker = new DatePickerFragment(context, this);
//                datePicker.show(getFragmentManager(), DATE_PICKER_TAG);
                showDatePicker();
            }
        });
    }

    private void showDatePicker(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                context,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

}

// paste this in init
//    MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
//        builder.setTitleText("Date picker");
//                builder.setTheme(R.style.MyCalendarTheme);
//final MaterialDatePicker materialDatePicker = builder.build();
//
//
//        btnDatePicker.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View view) {
//        materialDatePicker.show(getFragmentManager(), "Date_Picker");
//        }
//        });