package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private AlarmManager alarmManager;
    private PendingIntent pi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initAlarmManager();
        createNotificationChannel();

        findViewById(R.id.button_on).setOnClickListener(v -> {
            run();
            Toast.makeText(this, "ON", Toast.LENGTH_SHORT).show();
        });

        findViewById(R.id.button_off).setOnClickListener(v -> {
            if (alarmManager != null)
                alarmManager.cancel(pi);
            Toast.makeText(this, "OFF", Toast.LENGTH_SHORT).show();
        });
    }

    private void initAlarmManager() {
        Intent intent = new Intent(this, MyReceiver.class);
        alarmManager = (AlarmManager) this.getSystemService(ALARM_SERVICE);
        pi = PendingIntent.getBroadcast(this, 0, intent, 0);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("primary_notification_channel", "Title", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Description");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void run() {
        Calendar alarmStartTime = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        alarmStartTime.set(Calendar.HOUR_OF_DAY, 11);
        alarmStartTime.set(Calendar.MINUTE, 11);
        alarmStartTime.set(Calendar.SECOND, 0);
        if (now.after(alarmStartTime)) {
            alarmStartTime.add(Calendar.DATE, 1);
        }
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                alarmStartTime.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pi);
    }
}