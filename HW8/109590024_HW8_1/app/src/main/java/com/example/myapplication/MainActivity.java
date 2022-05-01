package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.battery);
        counter = 0;
    }

    public void up(View view) {
        counter = counter == 6 ? counter : counter + 1;
        imageView.setImageLevel(counter);
    }

    public void down(View view) {
        counter = counter == 0 ? counter : counter - 1;
        imageView.setImageLevel(counter);
    }
}