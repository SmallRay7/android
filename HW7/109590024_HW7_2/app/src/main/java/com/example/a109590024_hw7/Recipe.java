package com.example.a109590024_hw7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Recipe extends AppCompatActivity {
    private ImageView imageView;
    private Drawable drawable;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        Intent intent = getIntent();
        drawable = getResources().getDrawable(Integer.valueOf(intent.getStringExtra("img_url")));
        imageView.setImageDrawable(drawable);
        textView.setText(intent.getStringExtra("data"));
    }
}