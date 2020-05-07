package com.rojan.todo.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.rojan.todo.database.AppDatabase;
import com.rojan.todo.database.Repository;
import com.rojan.todo.model.Category;

import java.util.List;

public class AddCategoryFragmentViewModel extends AndroidViewModel {

    private LiveData<List<Category>> listCategory;
    private String categoryName;
    private int categoryId;

    public AddCategoryFragmentViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(application);
        Repository repository = new Repository(database);
        listCategory = repository.loadAllCategoryById();
    }

    public void saveIntoDatabase(){
        AppDatabase database = AppDatabase.getInstance(getApplication());
        Repository repository = new Repository(database);

        if (getCategoryId() == -1){
            Category category = new Category(getCategoryName());

            repository.insertCategory(category);
        }

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
