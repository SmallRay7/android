package com.example.finalproject;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.preference.PreferenceManager;

public class SetWeightActivity extends BaseActivity {

    private int weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_weight);

        weight = 60;

        updateWeight();
        initButton();
    }

    private void initButton() {
        findViewById(R.id.remove).setOnClickListener(view -> {
            weight--;
            updateWeight();
        });
        findViewById(R.id.add).setOnClickListener(view -> {
            weight++;
            updateWeight();
        });
        findViewById(R.id.compute).setOnClickListener(view -> compute());
    }

    private void updateWeight() {
        weight = Math.max(weight, 0);
        ((TextView) findViewById(R.id.weight)).setText(String.valueOf(weight));
    }

    private void compute() {
        SharedPreferences sp = getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        int water = weight * 30, cup = water / 240 + 1;

        editor.putInt("water", water);
        editor.putInt("cup", cup);
        editor.putInt("water_today", 0);
        editor.apply();

        Dialog dialog = getDialog("計算完成！", "適合你的水量為 " + water + " ml，約 " + cup + " 杯 240 ml 的水，開始植物保母吧！");
        dialog.findViewById(R.id.button).setOnClickListener(button -> startActivity(new Intent(SetWeightActivity.this, MainActivity.class)));
        dialog.show();
    }
}