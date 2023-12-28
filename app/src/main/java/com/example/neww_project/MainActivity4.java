package com.example.neww_project;

import static android.app.job.JobInfo.PRIORITY_HIGH;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.icu.util.Calendar;
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
    private NotificationManager notificationManager;
    NotificationManagerCompat manager;

    private static final int NOTIFY_ID = 101;
    private static final String CHANNEL_ID = "CHANNEL_ID";
    int id = 0;

    PendingIntent pendingIntent;
    AlarmManager alarmManager;

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

        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        //    NotificationChannel channel = new NotificationChannel("MyNotification", "MyNotification", NotificationManager.IMPORTANCE_DEFAULT);
        //    NotificationManager manager = getSystemService(NotificationManager.class);
        //    manager.createNotificationChannel(channel);
        //}
        //уведомления
        //NotificationCompat.Builder builder =
        //        new NotificationCompat.Builder(MainActivity4.this, "MyNotification")
        //                .setSmallIcon(android.R.drawable.ic_dialog_email)
        //                .setContentTitle("Title change").setContentText("Notification text change");

        //NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainActivity4.this);
        //if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
        //    return;
        //}
        //managerCompat.notify(1, builder.build());

        //Calendar notifyTime = Calendar.getInstance();
        //notifyTime.set(Calendar.HOUR_OF_DAY, 6);
        //notifyTime.set(Calendar.MINUTE, 15);
        //notifyTime.set(Calendar.SECOND, 0);
        //Intent intent = new Intent(this, NotificationPublisher.class);
        //PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, notifyTime.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);


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
                startActivity(new Intent(MainActivity4.this, NoteClass.class));
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
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    void videoPlay(int advice) {
        simpleVideoView = (VideoView) findViewById(R.id.simpleVideoView);

        if (mediaControls == null) {
            // create an object of media controller class
            mediaControls = new MediaController(MainActivity4.this);
            mediaControls.setAnchorView(simpleVideoView);
        }
        simpleVideoView.setMediaController(mediaControls);

        if (advice == 1) {
            simpleVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video1));
            name.setText("Уход за полостью рта");

        } else if (advice == 2) {
            simpleVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video1));
            name.setText("Уход за элайнером");
        } else if (advice == 3) {
            simpleVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video1));
            name.setText("Уход за трейнером");
        } else if (advice == 4) {
            simpleVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video4));
            name.setText("Уход за пластинкой");
        } else {
            simpleVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video4));
            Toast.makeText(this, advice, Toast.LENGTH_LONG).show();
        }

        simpleVideoView.start();
    }

    public static void setReminder(Context context, Class<?> cls, int hour, int min) {
        Calendar calendar = Calendar.getInstance();
        Calendar setcalendar = Calendar.getInstance();
        setcalendar.set(Calendar.HOUR_OF_DAY, hour);
        setcalendar.set(Calendar.MINUTE, min);
        setcalendar.set(Calendar.SECOND, 0);
        // cancel already scheduled reminders

        if (setcalendar.before(calendar))
            setcalendar.add(Calendar.DATE, 1);

        // Enable a receiver
        ComponentName receiver = new ComponentName(context, cls);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }
}

