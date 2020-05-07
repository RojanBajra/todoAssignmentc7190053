package com.rojan.todo.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.rojan.todo.model.Category;

import java.util.List;

@Dao
public interface CategoryDao {

    @Query("select * from category order by categoryName")
    LiveData<List<Category>> loadAllTheCAtegory();

    @Query("select * from category order by categoryId desc")
    LiveData<List<Category>> loadAllTheCAtegoryById();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategory(Category category);

    @Update
    void updateCategory(Category category);

    @Delete
    void deleteCategory(Category category);

}
