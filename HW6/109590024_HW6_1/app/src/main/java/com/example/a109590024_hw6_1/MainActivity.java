package com.example.a109590024_hw6_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showToast(View view){
        String toast = "Toppings: ";
        CheckBox c1 = (CheckBox) findViewById(R.id.checkBox);
        CheckBox c2 = (CheckBox) findViewById(R.id.checkBox2);
        CheckBox c3 = (CheckBox) findViewById(R.id.checkBox3);
        CheckBox c4 = (CheckBox) findViewById(R.id.checkBox4);
        CheckBox c5 = (CheckBox) findViewById(R.id.checkBox5);
        if(c1.isChecked())
            toast += "Chocolate syrup ";
        if(c2.isChecked())
            toast += "Sprinkeis ";
        if(c3.isChecked())
            toast += "Crushed nuts ";
        if(c4.isChecked())
            toast += "Cherries ";
        if(c5.isChecked())
            toast += "Orio cookie crumbles ";
        Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_LONG).show();
    }
}