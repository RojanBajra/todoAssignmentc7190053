package com.rojan.todo.utils;

import com.rojan.todo.model.Task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateFormatUtils {

    public static DateFormatUtils getInstance(){
        return new DateFormatUtils();
    }

    private DateFormatUtils(){

    }

    public String generateCurrentDate() {
        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        return df.format(c);
    }

    public String dateConverter(Date date, String datePattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        String dateVal = "";
        try {
            dateVal = dateFormat.format(date);
        } catch (Exception e) {

        }
        return dateVal;
    }
}
