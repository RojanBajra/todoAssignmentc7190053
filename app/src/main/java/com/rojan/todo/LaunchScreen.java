package com.rojan.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

public class LaunchScreen extends AppCompatActivity {

    private static int LAUNCH_SCREEN_DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_screen);
        launchScreen();
    }

    private void launchScreen() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                Intent intent = new Intent(getApplicationContext(), LaunchScreen.class);
                startActivity(TodoList.makeIntent(getApplicationContext()));
                finish();
            }
        }, LAUNCH_SCREEN_DELAY);
    }
}
