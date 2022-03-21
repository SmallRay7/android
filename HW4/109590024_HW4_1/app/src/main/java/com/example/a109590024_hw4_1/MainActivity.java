package com.example.a109590024_hw4_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("count");
            TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText(String.valueOf(count));
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("count", count);
    }

    public void countUp(View view) {
        count++;
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(String.valueOf(count));
    }
}