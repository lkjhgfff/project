package com.example.neww_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity3 extends AppCompatActivity {

    ImageButton simpleButton1, simpleButton2, simpleButton3;
    Button saveButton;
    CalendarView calendar;
    EditText time;
    TextInputEditText recomendation;
    String nowRecomendation = "",nowTime = "", dateq, monthq, yearq;


    @Override
    protected void onResume() {
        super.onResume();
        //FullScreencall();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);



        calendar = findViewById(R.id.simpleCalendarView);
        saveButton = (Button) findViewById(R.id.saveButton);
        SharedPreferences prefs = this.getSharedPreferences("com.example.neww_project", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        time = findViewById(R.id.time);
        recomendation = findViewById(R.id.recomendation);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                nowRecomendation = dayOfMonth + "/" + (month) + "/" + year + "/recomendation";
                nowTime = dayOfMonth + "/" + (month) + "/" + year + "/time";
                time.setText(prefs.getString(nowTime, ""));
                recomendation.setText(prefs.getString(nowRecomendation, ""));
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString(nowRecomendation, recomendation.getText().toString());
                editor.putString(nowTime, time.getText().toString());
                editor.commit();
            }
        });


        simpleButton1 = (ImageButton) findViewById(R.id.button1);//get id of button 1
        simpleButton2 = (ImageButton) findViewById(R.id.button2);//get id of button 1
        simpleButton3 = (ImageButton) findViewById(R.id.button4);//get id of button 1

        simpleButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity3.this, MainActivity.class));
            }
        });
        simpleButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity3.this, MainActivity2.class));
            }
        });
        simpleButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity3.this, MainActivity4.class));
            }
        });

    }
    public void FullScreencall() {
        if(Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if(Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }
}