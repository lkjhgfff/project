package com.example.neww_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity4 extends AppCompatActivity {

    ImageButton simpleButton1, simpleButton2, simpleButton3;
    VideoView simpleVideoView;
    MediaController mediaControls;
    TextView name;

    @Override
    protected void onResume() {
        super.onResume();
        //FullScreencall();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        name = findViewById(R.id.textView2);
        SharedPreferences prefs = this.getSharedPreferences("com.example.neww_project", Context.MODE_PRIVATE);
        videoPlay(prefs.getInt("key", 0));


        simpleButton1 = (ImageButton) findViewById(R.id.button1);//get id of button 1
        simpleButton2 = (ImageButton) findViewById(R.id.button2);//get id of button 1
        simpleButton3 = (ImageButton) findViewById(R.id.button3);//get id of button 1

        simpleButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity4.this, MainActivity.class));
            }
        });
        simpleButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity4.this, MainActivity2.class));
            }
        });
        simpleButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity4.this, MainActivity3.class));
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
    void videoPlay(int advice) {
        simpleVideoView = (VideoView)findViewById(R.id.simpleVideoView);

        if (mediaControls == null) {
            // create an object of media controller class
            mediaControls = new MediaController(MainActivity4.this);
            mediaControls.setAnchorView(simpleVideoView);
        }
        simpleVideoView.setMediaController(mediaControls);

        if (advice == 1) {
            simpleVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video1));
            name.setText("Уход за полостью рта");

        }
        else if (advice == 2) {
            simpleVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video1));
            name.setText("Уход за элайнером");
        }
        else if (advice == 3) {
            simpleVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video1));
            name.setText("Уход за трейнером");
        }
        else if (advice == 4) {
            simpleVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video4));
            name.setText("Уход за пластинкой");
        }
        else {
            simpleVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video4));
            Toast.makeText(this, advice, Toast.LENGTH_LONG).show();
        }

        simpleVideoView.start();
    }

}