package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {

    private View decorView;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        decorView = getWindow().getDecorView();
        dialog = new Dialog(this);

        removeActionBar();
    }

    protected Dialog getDialog(String title, String content) {
        dialog.setContentView(R.layout.dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ((TextView) dialog.findViewById(R.id.title)).setText(title);
        ((TextView) dialog.findViewById(R.id.content)).setText(content);
        dialog.findViewById(R.id.button).setOnClickListener(button -> dialog.dismiss());
        return dialog;
    }

    protected void showToast(int imageID, String text, float textSize) {
        View layout = getLayoutInflater().inflate(R.layout.toast, findViewById(R.id.toast_root));
        ((ImageView) layout.findViewById(R.id.toast_image)).setImageResource(imageID);
        ((TextView) layout.findViewById(R.id.toast_text)).setText(text);
        ((TextView) layout.findViewById(R.id.toast_text)).setTextSize(textSize);
        try {
            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();
        } catch (Exception e) {
            Looper.prepare();
            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();
            Looper.loop();
        }
    }

    private void removeActionBar() {
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus)
            removeActionBar();
    }
}