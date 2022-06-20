package com.example.finalproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.preference.PreferenceManager;

public class ChartActivity extends BaseActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        SharedPreferences sp = getSharedPreferences("pref", Context.MODE_PRIVATE);
        int water = sp.getInt("water", -1);
        int water_today = sp.getInt("water_today", -1);
        int percentage = Math.min((int) ((double) water_today / water * 100), 100);
        ((ImageView) findViewById(R.id.human)).setImageLevel(percentage);
        ((TextView) findViewById(R.id.title)).setText(percentage + " %");
        ((TextView) findViewById(R.id.content)).setText(water_today + " / " + water + " ml");

        initButton();
    }

    private void initButton() {
        findViewById(R.id.icon_home).setOnClickListener(view -> startActivity(new Intent(ChartActivity.this, MainActivity.class)));
        findViewById(R.id.icon_setting).setOnClickListener(view -> startActivity(new Intent(ChartActivity.this, SettingsActivity.class)));
    }
}