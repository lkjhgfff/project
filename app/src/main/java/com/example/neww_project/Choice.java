package com.example.neww_project;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Choice extends AppCompatActivity {

    SharedPreferences sharedpreference;
    ImageButton button1, button2, button3, button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        sharedpreference = PreferenceManager.getDefaultSharedPreferences(this.getBaseContext());



        ImageButton button1 = (ImageButton) findViewById(R.id.idImgBtn);
        ImageButton button2 = (ImageButton) findViewById(R.id.idImgBtn1);
        ImageButton button3 = (ImageButton) findViewById(R.id.idImgBtn2);
        ImageButton button4 = (ImageButton) findViewById(R.id.idImgBtn3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To edit shared preferences use editor as follows
                SharedPreferences.Editor editor = sharedpreference.edit();
                editor.putString("key","nesyomnie");
                editor.commit();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To edit shared preferences use editor as follows
                SharedPreferences.Editor editor = sharedpreference.edit();
                editor.putString("key","elainer");
                editor.commit();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To edit shared preferences use editor as follows
                SharedPreferences.Editor editor = sharedpreference.edit();
                editor.putString("key","plastinki");
                editor.commit();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To edit shared preferences use editor as follows
                SharedPreferences.Editor editor = sharedpreference.edit();
                editor.putString("key","treiner");
                editor.commit();
            }
        });
    }
}