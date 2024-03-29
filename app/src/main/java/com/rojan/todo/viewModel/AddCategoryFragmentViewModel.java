package com.rojan.todo.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.rojan.todo.database.AppDatabase;
import com.rojan.todo.database.Repository;
import com.rojan.todo.model.Category;
import com.rojan.todo.model.Task;

import java.util.List;

public class AddCategoryFragmentViewModel extends AndroidViewModel {

    private LiveData<List<Category>> listCategory;
    private String categoryName;
    private int categoryId;
    private Repository repository;

    public AddCategoryFragmentViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(application);
        repository = new Repository(database);
        listCategory = repository.loadAllCategory();
    }

    public void saveIntoDatabase(){
        AppDatabase database = AppDatabase.getInstance(getApplication());
        Repository repository = new Repository(database);

        if (getCategoryId() == -1){
            Category category = new Category(getCategoryName());

            repository.insertCategory(category);
        }

    }

    public void deleteCategory(Category category, int categoryId){
        repository.deleteTheCategory(category);
        repository.deleteTheTaskByCategoryId(categoryId);
    }

    public LiveData<List<Category>> getListCategory() {
        return listCategory;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
