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
import android.os.SystemClock;
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
    TextView name, recomendation;
    PendingIntent pendingIntent;

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
        recomendation = findViewById(R.id.recomendation);
        SharedPreferences prefs = this.getSharedPreferences("com.example.neww_project", Context.MODE_PRIVATE);
        videoPlay(prefs.getInt("key", 0));

        Intent alarmIntent = new Intent(MainActivity4.this, MyAlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity4.this, 0, alarmIntent, 0);
        start();

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
            simpleVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.breketi));
            name.setText("Уход за полостью рта");
            recomendation.setText("Соблюдайте режим ношения элайнеров. Помните, снимать элайнеры можно только на время еды или перед чисткой зубов. Все остальное время они должны быть надеты на зубы. Ортодонты советуют носить каппы минимум по 22 часа в сутки.\n" +
                    "Перед тем, как надеть элайнеры, обязательно хорошо почистите зубы. При этом неважно, какой зубной щеткой вы будете это делать. Подойдет и та, которой вы пользовались до начала лечения. Использование зубной нити приветствуется, а вот ополаскиватель для рта нужно выбирать с осторожностью — в его составе не должно быть компонентов, которые могут пересушить слизистую.\n" +
                    "Используйте зубную пасту, которая подходит для вашей эмали. Так как зубы приходится чистить часто, лучше выбирать мягкую пасту без абразивных частиц и эффекта отбеливания. Ею же можно чистить и элайнеры. Есть также специальное средство в виде таблеток для очистки ортодонтических конструкций. Используют его так: растворяют таблетку в теплой воде и на 3–5 минут опускают элайнеры в раствор.\n" +
                    "Если у вас чувствительные десны, лучше использовать ирригатор. Ирригатор эффективно очищает все труднодоступные места, при этом не травмирует.\n" +
                    "Элайнеры нужно снимать перед каждым приемом пищи или напитков. Пока вы будете есть, каппы нужно положить в специальный контейнер, чтобы предотвратить их поломку и не выбросить случайно вместе с использованной салфеткой. Воду в контейнер наливать не нужно.\n" +
                    "Перед тем как надеть каппы, обязательно нужно их почистить. Сделать это можно с помощью зубной щетки без пасты или просто промыть под струей воды. Ни в коем случае не мойте их слишком горячей водой — так вы повредите полимер, из которого изготовлены элайнеры, и они потеряют свою форму.\n" +
                    "Пациентам лучше воздержаться от курения, потому что элайнеры могут покрыться пленкой из-за осевших табачных смол. Они потемнеют, их будет труднее очистить.");

        } else if (advice == 2) {
            simpleVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video1));
            name.setText("Уход за элайнером");
            recomendation.setText("Во время еды каппы нужно снимать. Также с элайнерами во рту нельзя пить чай, кофе и любые другие горячие напитки. Под воздействием высокой температуры материал каппы деформируется, форма ее нарушается и полноценного лечебного действия уже не происходит. Не снимая каппы, можно пить только обычную воду без добавок, комнатной температуры. Пациент носит каппы практически целые сутки, снимая только во время еды и чистки зубов. Следует отказаться от вредной привычки курения или снимать элайнеры перед курением. Это связано и с деформацией материала горячим дымом, и с тем, что если курить сигареты с элайнерами в полости рта, на них очень скоро появится пленка темного налета, снять который будет затруднительно. Нужно уделять гигиене полости рта в период лечения повышенное внимание. Это поможет предотвратить появление заболеваний зубов и слизистой. Так как очень важно, чтобы за время лечения зубы не только стали ровными, но еще и остались здоровыми.");
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
    public void start() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 3);
        calendar.set(Calendar.MINUTE, 1);

        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 1000000;

        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), interval, pendingIntent);
    }

    public void notification (View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel("MyNotification", "MyNotification", NotificationManager.IMPORTANCE_DEFAULT);
                NotificationManager manager = getSystemService(NotificationManager.class);
                manager.createNotificationChannel(channel);
            }
            //уведомления
            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(this, "MyNotification")
                            .setSmallIcon(android.R.drawable.ic_dialog_email)
                            .setContentTitle("Title change").setContentText("Notification text change");

            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            managerCompat.notify(1, builder.build());
    }

}


