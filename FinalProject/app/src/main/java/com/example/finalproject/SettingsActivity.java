package com.example.finalproject;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;

import java.util.Calendar;

public class SettingsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initButton();
    }

    private void initButton() {
        findViewById(R.id.icon_chart).setOnClickListener(view -> startActivity(new Intent(SettingsActivity.this, ChartActivity.class)));
        findViewById(R.id.icon_home).setOnClickListener(view -> startActivity(new Intent(SettingsActivity.this, MainActivity.class)));

    }

    public static class SettingsFragment extends PreferenceFragmentCompat {

        private AlarmManager alarmManager;
        private PendingIntent pi;

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
            ((SwitchPreferenceCompat) findPreference("notification")).setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    if (!(boolean) newValue)
                        alarmManager.cancel(pi);
                    return true;
                }
            });
            ((ListPreference) findPreference("notification_time")).setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                        start((String) newValue);
                    return true;
                }
            });

            initAlarmManager();
            createNotificationChannel();
        }

        private void initAlarmManager() {
            Intent intent = new Intent(getActivity(), Receiver.class);
            alarmManager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);
            pi = PendingIntent.getBroadcast(getActivity(), 0, intent, 0);
        }

        private void createNotificationChannel() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel("primary_notification_channel", "Title", NotificationManager.IMPORTANCE_DEFAULT);
                channel.setDescription("Description");
                NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
                notificationManager.createNotificationChannel(channel);
            }
        }

        private void start(String hour) {
            Calendar alarmStartTime = Calendar.getInstance();
            Calendar now = Calendar.getInstance();
            alarmStartTime.set(Calendar.HOUR_OF_DAY, (now.getTime().getHours() + Integer.parseInt(hour)) % 24);
            alarmStartTime.set(Calendar.MINUTE, now.getTime().getMinutes());
            alarmStartTime.set(Calendar.SECOND, now.getTime().getSeconds());
            if (now.after(alarmStartTime)) {
                alarmStartTime.add(Calendar.DATE, 1);
            }
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                    alarmStartTime.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pi);
            Toast.makeText(getActivity(), alarmStartTime.getTime().getHours() + " : " + alarmStartTime.getTime().getMinutes() + " : " + alarmStartTime.getTime().getSeconds(), Toast.LENGTH_SHORT).show();
        }
    }
}