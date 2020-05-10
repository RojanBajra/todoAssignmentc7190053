package com.rojan.todo.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.rojan.todo.database.AppDatabase;
import com.rojan.todo.database.Repository;
import com.rojan.todo.model.Category;

import java.util.List;

public class ListCategoryFragmentViewModel extends AndroidViewModel {

    private LiveData<List<Category>> listCategory;

    public ListCategoryFragmentViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(application);
        Repository repository = new Repository(database);
        listCategory = repository.loadAllCategory();
    }

    public LiveData<List<Category>> getListCategory() {
        return listCategory;
    }

    public void setListCategory(LiveData<List<Category>> listCategory) {
        this.listCategory = listCategory;
    }
}
