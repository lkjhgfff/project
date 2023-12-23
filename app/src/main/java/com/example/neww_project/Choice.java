package com.example.neww_project;


import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Choice extends AppCompatActivity {

    SharedPreferences sharedpreference;
    ImageButton button1, button2, button3, button4;
    Button saveDevice;
    int dev;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        SharedPreferences prefs = this.getSharedPreferences("com.example.neww_project", Context.MODE_PRIVATE);
        //SharedPreferences prefss = this.getSharedPreferences("com.example.app", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();

        ImageButton button1 = (ImageButton) findViewById(R.id.idImgBtn);
        ImageButton button2 = (ImageButton) findViewById(R.id.idImgBtn1);
        ImageButton button3 = (ImageButton) findViewById(R.id.idImgBtn2);
        ImageButton button4 = (ImageButton) findViewById(R.id.idImgBtn3);
        Button saveDevice = (Button)findViewById(R.id.saveDevice);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To edit shared preferences use editor as follows
                dev = 1;
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To edit shared preferences use editor as follows
                dev = 2;
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To edit shared preferences use editor as follows
                dev = 3;
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To edit shared preferences use editor as follows
                dev = 4;
            }
        });

        saveDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To edit shared preferences use editor as follows
                editor.putInt("key",dev);
                editor.commit();
                startActivity(new Intent(Choice.this, MainActivity2.class));
            }
        });
    }
}