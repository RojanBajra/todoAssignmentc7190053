package com.rojan.todo.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.rojan.todo.model.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Task.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    private final static Object LOCK = new Object();
    private static String DATABASE_NAME = "todolistdb";
    private static AppDatabase shareInstance;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(1);

    public static AppDatabase getInstance(Context context){
        if(shareInstance == null){
            synchronized (LOCK){
                shareInstance = Room.databaseBuilder(
                        context.getApplicationContext(),
                        AppDatabase.class,
                        AppDatabase.DATABASE_NAME
                ).build();
            }
        }
        return shareInstance;
    }

    public abstract TaskDao taskDao();

}
