package com.rojan.todo.viewModel;

import androidx.lifecycle.ViewModel;

public class SingleTaskViewModel extends ViewModel {
    int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
