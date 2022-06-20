package com.example.finalproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class InitActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!getSharedPreferences("pref", Context.MODE_PRIVATE).contains("water"))
                    startActivity(new Intent(InitActivity.this, SetWeightActivity.class));
                else
                    startActivity(new Intent(InitActivity.this, MainActivity.class));
                finish();
            }
        }, 1000);
    }
}