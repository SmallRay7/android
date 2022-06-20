package com.example.finalproject;

import androidx.preference.PreferenceManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity {

    private int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time = 10;
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                time++;
            }
        }, 0, 1000);

        initButton();
        update();
    }

    private void initButton() {
        findViewById(R.id.waterBtn).setOnClickListener(view -> water());
        findViewById(R.id.icon_chart).setOnClickListener(view -> startActivity(new Intent(MainActivity.this, ChartActivity.class)));
        findViewById(R.id.icon_setting).setOnClickListener(view -> startActivity(new Intent(MainActivity.this, SettingsActivity.class)));
    }

    private void update() {
        updatePlant();
        updateCup();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void updatePlant() {
        SharedPreferences sp = getSharedPreferences("pref", Context.MODE_PRIVATE);
        ImageView imageView = findViewById(R.id.plant);
        if (sp.getInt("water_today", -1) >= 1000)
            imageView.setImageDrawable(getDrawable(R.drawable.plant_2));
        else
            imageView.setImageDrawable(getDrawable(R.drawable.plant_1));
    }

    private void updateCup() {
        SharedPreferences sp = getSharedPreferences("pref", Context.MODE_PRIVATE);
        int water = sp.getInt("water", -1);
        int water_today = sp.getInt("water_today", -1);
        int cup_size = Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(this).getString("cup_size", "240"));
        int cup = (int) ((double) (water - water_today) / cup_size + 0.999);
        cup = water_today > water ? 0 : cup;
        ((TextView) findViewById(R.id.cup)).setText(cup > 0 ? String.valueOf(cup) : "");
    }

    private void water() {
        SharedPreferences sp = getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        int water = sp.getInt("water", -1);
        int water_today = sp.getInt("water_today", -1);
        int cup_size = Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(this).getString("cup_size", "240"));
        boolean isOver = water_today >= water;

        if (water_today == 0)
            getDialog("你今天的第一杯水！", "記得按時回來喝杯水，植物才會保持健康喔！").show();

        water_today += cup_size;
        editor.putInt("water_today", water_today);
        editor.apply();

        int cup = (int) ((double) (water - water_today) / cup_size + 0.999);
        cup = water_today > water ? 0 : cup;
        if (cup > 0) {
            showToast(R.drawable.ic_human, "今天還需要 " + cup + " 杯水", 18);
            if (time < 10) {
                new Timer().scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        showToast(R.drawable.ic_time, "一下子喝太多水對身體也不太好喔！\n稍微休息一下再補充水分吧！", 14);
                        finish();
                    }
                }, 3000, 3000);
            }
        } else if (isOver)
            showToast(R.drawable.ic_time, "小心！一天不要攝取太多水份，\n對身體也不好喔！", 14);
        time = 0;

        if (water_today >= water && !isOver)
            getDialog("完美！", "恭喜你！已經達成今天的目標！").show();

        update();
    }
}